package com.sorsix.cookitup.model.dto

data class UserInfoDTO(
    private val firstname: String,
    private val lastname: String,
    private val username: String,
    private val roles: List<String> ){
}