package com.nmb.springkotlinpostservice.mapper

import com.nmb.springkotlinpostservice.dto.RegisterRequest
import com.nmb.springkotlinpostservice.entity.User
import org.mapstruct.Mapper
import org.mapstruct.factory.Mappers

@Mapper
interface UserMapper {
    companion object {
        val INSTANCE: UserMapper = Mappers.getMapper(UserMapper::class.java)
    }

    fun registerRequestToUser(request: RegisterRequest): User
}