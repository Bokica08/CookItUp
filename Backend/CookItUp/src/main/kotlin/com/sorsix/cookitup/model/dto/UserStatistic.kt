package com.sorsix.cookitup.model.dto

data class UserStatistic(
    val id: Long,
    val username: String,
    var recipesCreated: Int,
    var reviewsCreated: Int,
    var ordersCreated: Int,
    val email: String,
    val role: String,
)
