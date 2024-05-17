package com.nmb.springkotlinpostservice.service

import com.nmb.springkotlinpostservice.dto.LoginRequest
import com.nmb.springkotlinpostservice.dto.RegisterRequest
import com.nmb.springkotlinpostservice.entity.Role
import com.nmb.springkotlinpostservice.exception.NotFoundException
import com.nmb.springkotlinpostservice.mapper.UserMapper
import com.nmb.springkotlinpostservice.repository.RoleRepository
import com.nmb.springkotlinpostservice.repository.UserRepository
import com.nmb.springkotlinpostservice.util.JwtUtil
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userRepository: UserRepository,
    private val roleRepository: RoleRepository,
    private val passwordEncoder: PasswordEncoder,
    private val jwtUtil: JwtUtil,
    private val authenticationManager: AuthenticationManager
) : UserDetailsService {
    override fun loadUserByUsername(username: String): UserDetails {
        val user = userRepository.findByUsername(username) ?: throw NotFoundException("User $username not found")
        return org.springframework.security.core.userdetails.User(
            user.username,
            user.password,
            user.roles.map { org.springframework.security.core.authority.SimpleGrantedAuthority(it.name) })
    }

    fun register(request: RegisterRequest) {
        val role = roleRepository.findByName("ROLE_USER") ?: Role("ROLE_USER").also { roleRepository.save(it) }
        val user = UserMapper.INSTANCE.registerRequestToUser(request).apply {
            this.password = passwordEncoder.encode(request.password)
            this.roles = setOf(role)
        }
        userRepository.save(user)
    }

    fun login(request: LoginRequest): String {
        authenticationManager.authenticate(UsernamePasswordAuthenticationToken(request.username, request.password))
        val userDetails = loadUserByUsername(request.username)
        return jwtUtil.generateToken(userDetails)
    }
}