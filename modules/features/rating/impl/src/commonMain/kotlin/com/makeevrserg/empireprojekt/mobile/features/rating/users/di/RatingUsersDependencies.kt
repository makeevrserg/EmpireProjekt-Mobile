package com.makeevrserg.empireprojekt.mobile.features.rating.users.di

import com.makeevrserg.empireprojekt.mobile.features.rating.users.data.RatingUsersRepository

internal interface RatingUsersDependencies {
    val ratingUsersRepository: RatingUsersRepository
    class Default(override val ratingUsersRepository: RatingUsersRepository) : RatingUsersDependencies
}
