package com.sorsix.cookitup.configuration

import com.sorsix.cookitup.model.User
import java.util.Date

import javax.servlet.http.Cookie
import javax.servlet.http.HttpServletRequest

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.ResponseCookie
import org.springframework.stereotype.Component
import org.springframework.web.util.WebUtils

import io.jsonwebtoken.*


@Component
class JwtUtils {
    @Value("\${bezkoder.app.jwtSecret}")
    private val jwtSecret: String? = null

    @Value("\${bezkoder.app.jwtExpirationMs}")
    private val jwtExpirationMs = 0

    @Value("\${bezkoder.app.jwtCookieName}")
    private var jwtCookie: String?=null
    fun getJwtFromCookies(request: HttpServletRequest): String? {
        val cookie:Cookie?=WebUtils.getCookie(request,jwtCookie!!)
        if(cookie!=null)
        {
            return cookie.value
        }
        return null
    }

    fun generateJwtCookie(userPrincipal: User): ResponseCookie {
        val jwt = generateTokenFromUsername(userPrincipal.username)
        return ResponseCookie.from(jwtCookie!!, jwt).path("/").maxAge((24 * 60 * 60).toLong()).sameSite("None")
            .secure(true).httpOnly(true).build()
    }

    fun getCleanJwtCookie(): ResponseCookie {
        return ResponseCookie.from(jwtCookie!!, "").path("/").sameSite("None").secure(true).httpOnly(true).build()
    }
    fun getUserNameFromJwtToken(token: String): String {
        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).body.subject
    }

     fun validateJwtToken(authToken: String): Boolean {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken)
            return true
        } catch (e: SignatureException) {
            logger.error("Invalid JWT signature: {}", e.message)
        } catch (e: MalformedJwtException) {
            logger.error("Invalid JWT token: {}", e.message)
        } catch (e: ExpiredJwtException) {
            logger.error("JWT token is expired: {}", e.message)
        } catch (e: UnsupportedJwtException) {
            logger.error("JWT token is unsupported: {}", e.message)
        }
        return false
    }

    fun generateTokenFromUsername(username: String): String {
        return Jwts.builder()
            .setSubject(username)
            .setIssuedAt(Date())
            .setExpiration(Date(System.currentTimeMillis() + jwtExpirationMs))
            .signWith(SignatureAlgorithm.HS512, jwtSecret)
            .compact()
    }

    companion object {
        private val logger: Logger = LoggerFactory.getLogger(JwtUtils::class.java)
    }
}