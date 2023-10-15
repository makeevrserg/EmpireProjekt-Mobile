package com.makeevrserg.empireprojekt.mobile.api.empireapi.impl

import com.makeevrserg.empireprojekt.mobile.api.empireapi.RatingApi
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.request.url
import ru.astrainteractive.empireapi.models.rating.RatingListRequest
import ru.astrainteractive.empireapi.models.rating.RatingModel
import ru.astrainteractive.empireapi.models.rating.RatingUserModel
import ru.astrainteractive.empireapi.models.rating.UserRatingsRequest
import ru.astrainteractive.empireapi.models.response.GenericPagedModel

class RatingApiImpl(
    private val httpClient: HttpClient,
    private val baseUrl: String = "http://192.168.1.3:8080"
) : RatingApi {

    override suspend fun users(body: RatingListRequest): GenericPagedModel<RatingUserModel> {
        return httpClient.post {
            url("$baseUrl/rating/users")
            setBody(body)
        }.body()
    }

    override suspend fun user(id: Long): RatingUserModel {
        return httpClient.get {
            url("$baseUrl/rating/user")
            parameter("user_id", id)
        }.body()
    }

    override suspend fun ratings(body: UserRatingsRequest): GenericPagedModel<RatingModel> {
        return httpClient.post {
            url("$baseUrl/rating/user/votes")
            setBody(body)
        }.body()
    }
}
