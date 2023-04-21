package com.sorsix.cookitup.repository

import com.sorsix.cookitup.model.PendingAdmin
import org.springframework.data.jpa.repository.JpaRepository

interface PendingAdminRepository:JpaRepository<PendingAdmin,Long> {
    fun findByUsername(username:String?):PendingAdmin
}