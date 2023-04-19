package com.sorsix.cookitup.controller

import com.sorsix.cookitup.configuration.JwtUtils
import com.sorsix.cookitup.model.User
import com.sorsix.cookitup.model.dto.LoginDTO
import com.sorsix.cookitup.model.dto.RegisterDTO
import com.sorsix.cookitup.model.dto.UserInfoDTO
import com.sorsix.cookitup.service.UserService
import org.springframework.http.HttpHeaders
import org.springframework.http.ResponseCookie
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.*
import javax.servlet.http.Cookie
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import javax.validation.Valid

@CrossOrigin(origins = ["http://localhost:4200"], allowCredentials = "true", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
class AuthController(
    private val authenticationManager: AuthenticationManager,
    private val jwtUtils: JwtUtils,
    private val userService: UserService
) {
    @PostMapping("/login")
    fun authenticateUser(@Valid @RequestBody loginRequest: LoginDTO, request:HttpServletRequest,response:HttpServletResponse): ResponseEntity<Any> {
        val authentication: Authentication = authenticationManager
            .authenticate(UsernamePasswordAuthenticationToken(loginRequest.username, loginRequest.password))
        SecurityContextHolder.getContext().authentication = authentication
        val userDetails: User = authentication.principal as User
        val jwtCookie = jwtUtils.generateJwtCookie(userDetails)
        val roles: List<String> = userDetails.authorities?.stream()
            ?.map { obj: GrantedAuthority? -> obj!!.authority }!!.toList()
        request.session.setAttribute("username",userDetails.username)
        val userInfo:UserInfoDTO=UserInfoDTO(
            firstname = userDetails.firstname!!, lastname = userDetails.lastname!!,
            username = userDetails.username, role = roles

        )
        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE,jwtCookie.toString())
            .body<Any>(
                userInfo

            )
    }

    @PostMapping("/logout")
    fun logoutUser(request: HttpServletRequest): ResponseEntity<*> {
        val cookie = jwtUtils.getCleanJwtCookie()
        request.session.removeAttribute("username")
        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString())
            .body<String>("You've been signed out!")
    }

    @PostMapping("/register")
    fun register(@RequestBody registerDTO: RegisterDTO): ResponseEntity<User> {
        return ResponseEntity.ok(userService.register(registerDTO))
    }
}