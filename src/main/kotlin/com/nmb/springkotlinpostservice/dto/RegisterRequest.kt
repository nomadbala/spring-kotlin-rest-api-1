package com.nmb.springkotlinpostservice.dto

data class RegisterRequest(
    val username: String,
    val password: String,
    val email: String
)