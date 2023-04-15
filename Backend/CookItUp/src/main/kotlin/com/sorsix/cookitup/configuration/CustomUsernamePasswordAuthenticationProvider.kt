package com.sorsix.cookitup.configuration

import com.sorsix.cookitup.service.UserService
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.AuthenticationException
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component


@Component
class CustomUsernamePasswordAuthenticationProvider(
    private val userService: UserService,
    private val passwordEncoder: PasswordEncoder
) :
    AuthenticationProvider {
    @Throws(AuthenticationException::class)
    override fun authenticate(authentication: Authentication): Authentication {
        val username: String = authentication.name
        val password: String = authentication.credentials.toString()
        if ("" == username || "" == password) {
            throw BadCredentialsException("Invalid Credentials")
        }
        val user: UserDetails = userService.loadUserByUsername(username)
        if (!passwordEncoder.matches(user.password, password)) {
            throw BadCredentialsException("Password is incorrect!")
        }
        return UsernamePasswordAuthenticationToken(user, user.password, user.authorities)
    }

    override fun supports(authentication: Class<*>): Boolean {
        return authentication == UsernamePasswordAuthenticationToken::class.java
    }
}