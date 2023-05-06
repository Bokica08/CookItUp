package com.sorsix.cookitup.service

import com.sorsix.cookitup.model.dto.UserStatistic

interface AdminService {
    fun getUsersStatistic(): List<UserStatistic>
    fun getCustomersCreated(): Long
    fun getOrdersCreated(): Long
    fun getReviewsCreated():Long
    fun getRecipesCreated():Long
}