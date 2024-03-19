package com.makeevrserg.empireprojekt.mobile.api.empireapi.impl

import com.makeevrserg.empireprojekt.mobile.api.empireapi.RatingApi
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.request.url
import io.ktor.http.ContentType
import io.ktor.http.contentType
import ru.astrainteractive.empireapi.models.rating.RatingModel
import ru.astrainteractive.empireapi.models.rating.RatingUserModel
import ru.astrainteractive.empireapi.models.rating.RatingsFilterModel
import ru.astrainteractive.empireapi.models.rating.UserRatingsRequest
import ru.astrainteractive.empireapi.models.response.GenericPagedModel

internal class RatingApiImpl(
    private val httpClient: HttpClient,
    private val baseUrl: String
) : RatingApi {

    override suspend fun users(page: Int, size: Int, body: RatingsFilterModel): GenericPagedModel<RatingUserModel> {
        return httpClient.post {
            url("$baseUrl/rating/users")
            parameter("page", page)
            parameter("size", size)
            contentType(ContentType.Application.Json)
            setBody(body)
        }.body()
    }

    override suspend fun user(id: Long): RatingUserModel {
        return httpClient.get {
            url("$baseUrl/rating/user")
            contentType(ContentType.Application.Json)
            parameter("user_id", id)
        }.body()
    }

    override suspend fun ratings(page: Int, size: Int, userId: Long): GenericPagedModel<RatingModel> {
        return httpClient.post {
            url("$baseUrl/rating/user/votes")
            parameter("page", page)
            parameter("size", size)
            contentType(ContentType.Application.Json)
            setBody(UserRatingsRequest(id = userId))
        }.body()
    }
}
