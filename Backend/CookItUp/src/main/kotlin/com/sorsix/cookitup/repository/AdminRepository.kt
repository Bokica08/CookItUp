package com.sorsix.cookitup.repository

import com.sorsix.cookitup.model.Admin
import org.springframework.data.jpa.repository.JpaRepository

interface AdminRepository:JpaRepository<Admin,Long>