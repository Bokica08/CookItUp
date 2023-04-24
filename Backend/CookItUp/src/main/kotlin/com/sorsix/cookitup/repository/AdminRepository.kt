package com.sorsix.cookitup.repository

import com.sorsix.cookitup.model.Admin
import com.sorsix.cookitup.model.PendingAdmin
import com.sorsix.cookitup.model.User
import org.springframework.data.jpa.repository.JpaRepository

interface AdminRepository:JpaRepository<Admin,Long> {
}