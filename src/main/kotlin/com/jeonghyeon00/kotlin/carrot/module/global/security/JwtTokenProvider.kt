package com.jeonghyeon00.kotlin.carrot.module.global.security

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Component
import java.security.Key
import java.util.Date
import javax.annotation.PostConstruct
import javax.servlet.http.HttpServletRequest

@Component
class JwtTokenProvider(private val userDetailsService: UserDetailsService) {
    @Value("\${JWT-SECRET}")
    private lateinit var secretKey: String
    lateinit var key: Key

    private val tokenExpireTime = 30 * 60 * 1000L

    @PostConstruct
    protected fun init() {
        this.key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey))
    }

    fun createToken(authentication: Authentication): String {
        val now = Date()
        return Jwts.builder()
            .setHeaderParam("typ", "JWT")
            .setSubject(authentication.name)
            .claim("Authorities", authentication.authorities.toString())
            .setIssuedAt(now)
            .setExpiration(Date(now.time + tokenExpireTime))
            .signWith(key, SignatureAlgorithm.HS256)
            .compact()
    }

    fun getAuthentication(token: String): Authentication {
        val userDetails = userDetailsService.loadUserByUsername(getUserPk(token))
        return UsernamePasswordAuthenticationToken(userDetails, "", userDetails.authorities)
    }

    fun getUserPk(token: String): String {
        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).body.subject
    }

    fun resolveToken(request: HttpServletRequest): String? {
        return request.getHeader("Authorization")
    }

    fun validateToken(jwtToken: String): Boolean {
        return try {
            val claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(jwtToken)
            !claims.body.expiration.before(Date())
        } catch (e: Exception) {
            false
        }
    }
}
