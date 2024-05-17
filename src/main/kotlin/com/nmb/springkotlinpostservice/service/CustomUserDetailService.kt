package com.nmb.springkotlinpostservice.service

import com.nmb.springkotlinpostservice.repository.UserRepository
import org.springframework.context.annotation.Primary
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
@Primary
class CustomUserDetailService(
    private val repository: UserRepository
) : UserDetailsService {
    override fun loadUserByUsername(username: String): UserDetails {
        val user = repository.findByUsername(username) ?: throw UsernameNotFoundException("User $username not found")
        return org.springframework.security.core.userdetails.User(
            user.username,
            user.password,
            user.roles.map { org.springframework.security.core.authority.SimpleGrantedAuthority(it.name) })
    }
}