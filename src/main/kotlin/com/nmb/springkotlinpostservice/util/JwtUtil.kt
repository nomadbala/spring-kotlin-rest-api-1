package com.nmb.springkotlinpostservice.util


import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Component
import java.util.*
import javax.crypto.SecretKey

@Component
class JwtUtil {
//    private final val SECRET_KEY: SecretKey =
//        Keys.hmacShaKeyFor(Decoders.BASE64.decode(System.getenv("JWT_SECRET_KEY")))

    // TODO: secretKeyFor is deprecated method
    private final val SECRET_KEY: SecretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256)


    fun extractUsername(token: String): String =
        extractClaim(token, Claims::getSubject)

    fun <T> extractClaim(token: String, claimsResolver: (Claims) -> T): T {
        val claims = extractAllClaims(token)
        return claimsResolver(claims)
    }

    fun extractAllClaims(token: String): Claims {
        return Jwts.parser().verifyWith(SECRET_KEY).build().parseSignedClaims(token).payload
    }

    fun generateToken(userDetails: UserDetails): String {
        val claims: Map<String, Any> = HashMap()
        return createToken(claims, userDetails.username)
    }

    fun createToken(claims: Map<String, Any>, subject: String): String {
        val currentTimeInMs = System.currentTimeMillis()

        return Jwts
            .builder()
            .claims(claims)
            .subject(subject)
            .issuedAt(Date(currentTimeInMs))
            .expiration(Date(currentTimeInMs + 1000 * 60 * 60 * 10))
            .signWith(SECRET_KEY, Jwts.SIG.HS256)
            .compact()
    }

    fun validateToken(token: String, userDetails: UserDetails): Boolean {
        val username = extractUsername(token)
        return (username == userDetails.username && !isTokenExpired(token))
    }

    fun isTokenExpired(token: String): Boolean {
        return extractExpiration(token).before(Date())
    }

    fun extractExpiration(token: String): Date {
        return extractClaim(token, Claims::getExpiration)
    }
}