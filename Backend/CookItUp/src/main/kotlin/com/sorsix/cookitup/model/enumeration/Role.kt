package com.sorsix.cookitup.model.enumeration

import org.springframework.security.core.GrantedAuthority


enum class Role : GrantedAuthority {
    ROLE_ADMIN,
    ROLE_USER,
    ROLE_PENDING_ADMIN;

    override fun getAuthority(): String {
        return name
    }
}