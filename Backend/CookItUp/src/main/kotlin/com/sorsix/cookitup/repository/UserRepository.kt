package com.sorsix.cookitup.repository

import com.sorsix.cookitup.model.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : JpaRepository<User, Long> {
    fun findByUserId(userId: Long) : User
    fun findAllByUsername(username: String) : List<User>
    fun existsByUsernameAndPassword(username: String, password: String) : Boolean
}