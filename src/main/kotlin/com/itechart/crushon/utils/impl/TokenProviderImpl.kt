package com.itechart.crushon.utils.impl

import com.itechart.crushon.utils.TokenProvider
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jws
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.util.*
import javax.annotation.PostConstruct

@Component
class TokenProviderImpl(
    @Value("\${jwt.token.secret}")
    private var secret: String,

    @Value("\${jwt.token.expires}")
    private val expires: Long, //Milliseconds

    @Value("\${jwt.token.expire-multiplier}")
    private val multiplier: Long //Milliseconds
): TokenProvider {

    @PostConstruct
    private fun init() {
        secret = Base64.getEncoder().encodeToString(secret.encodeToByteArray())
    }

    override fun createAuthorizationToken(username: String): String {
        return createToken(username, 1)
    }

    override fun createRefreshToken(username: String): String {
        return createToken(username, multiplier)
    }

    override fun validateToken(token: String): Jws<Claims> {
        try {
            val claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token)

            if(claims.body.expiration.before(Date())) {
                throw Exception("Invalid JWT")
            }

            return claims
        } catch (ex: Exception) {
            throw Exception("Invalid JWT")
        }
    }

    override fun isTokenValid(token: String): Boolean {
        return try {
            val claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token)

            !claims.body.expiration.before(Date())
        } catch (ex: Exception) {
            false
        }
    }

    override fun getUsernameFromToken(token: String): String =
        Jwts.parser().setSigningKey(secret).parseClaimsJws(token).body.subject

    private fun createToken(username: String, expireMultiplier: Long): String {
        val claims = Jwts.claims().setSubject(username)

        val now = Date()
        val validity = Date(now.time + expires * expireMultiplier)

        return Jwts.builder()
            .setClaims(claims)
            .setIssuedAt(now)
            .setExpiration(validity)
            .signWith(SignatureAlgorithm.HS256, secret)
            .compact()
    }
}
