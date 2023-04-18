package com.sorsix.cookitup.repository

import com.sorsix.cookitup.model.Customer
import com.sorsix.cookitup.model.dto.CustomerInfoDTO
import com.sorsix.cookitup.model.dto.UserInfoDTO
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CustomerRepository : JpaRepository<Customer, Long> {
    fun getByUsername(username: String): Customer
    fun findByUserId(id:Long):Customer
    fun findByUsername(username: String) : CustomerInfoDTO


}