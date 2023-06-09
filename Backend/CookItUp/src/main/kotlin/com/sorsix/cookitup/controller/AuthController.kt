package com.sorsix.cookitup.controller

import com.sorsix.cookitup.configuration.JwtUtils
import com.sorsix.cookitup.model.User
import com.sorsix.cookitup.model.dto.LoginDTO
import com.sorsix.cookitup.model.dto.MessageResponse
import com.sorsix.cookitup.model.dto.RegisterDTO
import com.sorsix.cookitup.model.dto.UserInfoDTO
import com.sorsix.cookitup.service.UserService
import org.springframework.http.HttpHeaders
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.*
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
    fun authenticateUser(@Valid @RequestBody loginRequest: LoginDTO): ResponseEntity<Any> {
        val authentication: Authentication = authenticationManager
            .authenticate(UsernamePasswordAuthenticationToken(loginRequest.username, loginRequest.password))
        SecurityContextHolder.getContext().authentication = authentication
        val userDetails: User = authentication.principal as User
        val jwtCookie = jwtUtils.generateJwtCookie(userDetails)
        val roles: List<String> = userDetails.authorities?.stream()
            ?.map { obj: GrantedAuthority? -> obj!!.authority }!!.toList()
        val userInfo =UserInfoDTO(
            firstname = userDetails.firstname, lastname = userDetails.lastname,
            username = userDetails.username, role = roles
        )
        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE,jwtCookie.toString())
            .body(
                userInfo
        )
    }

    @PostMapping("/logout")
    fun logoutUser(): ResponseEntity<MessageResponse> {
        val cookie = jwtUtils.getCleanJwtCookie()
        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE,cookie.toString())
            .body(MessageResponse("You have been logged out"))
    }

    @PostMapping("/register")
    fun register(@RequestBody registerDTO: RegisterDTO): ResponseEntity<User> {
        return ResponseEntity.ok(userService.register(registerDTO))
    }
    @GetMapping("existsByUsernameOrEmail")
    fun existsByUsernameOrEmail(@RequestParam username:String,@RequestParam email:String):Boolean
    {
        return userService.findIfExists(username,email)
    }

}