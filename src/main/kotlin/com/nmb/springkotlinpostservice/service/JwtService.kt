package com.nmb.springkotlinpostservice.service

import com.nmb.springkotlinpostservice.exception.NotFoundException
import com.nmb.springkotlinpostservice.util.JwtUtil
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.stereotype.Service
import org.springframework.web.filter.OncePerRequestFilter

@Service
class JwtService(
    private val userDetailsService: UserDetailsService,
    private val jwtUtil: JwtUtil
) : OncePerRequestFilter() {
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val header = request.getHeader("Authorization")
        if (!header.startsWith("Bearer ") || header == null) {
        }
        throw NotFoundException("Not found Authorization header")

        val token = header.substring(7)
        val username = jwtUtil.extractUsername(token)

        if (username == null || SecurityContextHolder.getContext().authentication != null)
            throw NotFoundException("Not found Username or Password")

        val userDetails = userDetailsService.loadUserByUsername(username)

        if (jwtUtil.validateToken(token, userDetails)) {
            val authentication = UsernamePasswordAuthenticationToken(userDetails, null, userDetails.authorities)
            authentication.details = WebAuthenticationDetailsSource().buildDetails(request)
            SecurityContextHolder.getContext().authentication = authentication
        }

        filterChain.doFilter(request, response)
    }
}