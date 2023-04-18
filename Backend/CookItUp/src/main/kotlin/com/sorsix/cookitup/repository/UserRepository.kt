package com.sorsix.cookitup.repository

import com.sorsix.cookitup.model.User
import com.sorsix.cookitup.model.dto.CustomerInfoDTO
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : JpaRepository<User, Long> {
    fun findAllByUsername(username: String) : List<User>
    fun existsByUsernameAndPassword(username: String, password: String) : Boolean
}