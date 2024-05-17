package com.nmb.springkotlinpostservice.mapper

import com.nmb.springkotlinpostservice.dto.PostDto
import com.nmb.springkotlinpostservice.entity.Post
import org.mapstruct.Mapper
import org.mapstruct.factory.Mappers

@Mapper
interface PostMapper {
    companion object {
        val INSTANCE: PostMapper = Mappers.getMapper(PostMapper::class.java)
    }

    fun postDtoToPost(dto: PostDto): Post
    fun postToPostDto(post: Post): PostDto
}