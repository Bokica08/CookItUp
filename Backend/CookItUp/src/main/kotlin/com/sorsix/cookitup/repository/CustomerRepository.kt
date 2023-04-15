package com.sorsix.cookitup.repository

import com.sorsix.cookitup.model.Customer
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CustomerRepository : JpaRepository<Customer, Long> {
    fun getByUsername(username: String): Customer
}