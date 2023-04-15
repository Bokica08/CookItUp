package com.sorsix.cookitup.service

import com.sorsix.cookitup.model.Customer
import com.sorsix.cookitup.model.User
import com.sorsix.cookitup.model.dto.RegisterDTO
import org.springframework.security.core.userdetails.UserDetailsService

interface UserService : UserDetailsService {
    override fun loadUserByUsername(username: String): User
    fun register(userDTO: RegisterDTO): User
    fun getUser(username: String, password: String): User
    fun getCustomerByUsername(username: String): Customer
}