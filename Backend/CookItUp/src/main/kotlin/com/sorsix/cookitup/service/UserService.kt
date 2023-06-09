package com.sorsix.cookitup.service

import com.sorsix.cookitup.model.Admin
import com.sorsix.cookitup.model.Customer
import com.sorsix.cookitup.model.User
import com.sorsix.cookitup.model.dto.CustomerInfoDTO
import com.sorsix.cookitup.model.dto.RegisterDTO
import com.sorsix.cookitup.model.enumeration.Role
import org.springframework.security.core.userdetails.UserDetailsService
import java.util.*

interface UserService : UserDetailsService {
    override fun loadUserByUsername(username: String): User
    fun register(userDTO: RegisterDTO): User
    fun getCustomerByUsername(username: String): Customer
    fun findByUsername(username: String) : CustomerInfoDTO
    fun addToFavorites(username:String,id:Long):Any
    fun deleteFromFavorites(username:String,id:Long):Any
    fun findAllByRole(role:Role):List<User>
    fun findAllPendingAdmins(): List<User?>?
    fun authorizePendingAdmin(username: String?): Optional<User>
    fun getRandomAdmin():Admin
    fun findIfExists(username:String,email: String):Boolean
    fun getUserByUsername(username: String): User

}