package com.sorsix.cookitup.service

import com.sorsix.cookitup.model.User
import com.sorsix.cookitup.model.dto.RegisterDTO

interface UserService {
    fun loadUserByUsername(username: String): User
    fun register(userDTO: RegisterDTO?): User
    fun getUser(username: String, password: String): User
}