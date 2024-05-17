package com.nmb.springkotlinpostservice.controller

import com.nmb.springkotlinpostservice.dto.LoginRequest
import com.nmb.springkotlinpostservice.dto.RegisterRequest
import com.nmb.springkotlinpostservice.service.UserService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/auth")
class AuthenticationController(
    private val userService: UserService,
) {
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    fun register(@RequestBody request: RegisterRequest) =
        userService.register(request)

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    fun login(@RequestBody request: LoginRequest): String =
        userService.login(request)
}