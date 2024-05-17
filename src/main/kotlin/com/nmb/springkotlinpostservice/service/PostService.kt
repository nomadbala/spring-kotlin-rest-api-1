package com.nmb.springkotlinpostservice.service

import com.nmb.springkotlinpostservice.dto.PostDto
import com.nmb.springkotlinpostservice.exception.NotFoundException
import com.nmb.springkotlinpostservice.mapper.PostMapper
import com.nmb.springkotlinpostservice.repository.PostRepository
import org.springframework.stereotype.Service

@Service
class PostService(
    private val repository: PostRepository
) {
    fun createPost(username: String, postDto: PostDto) {
        val post = PostMapper.INSTANCE.postDtoToPost(postDto).apply {
            this.username = username
        }
        repository.save(post)
    }

    fun getPost(id: Long): PostDto {
        val post = repository.findById(id).orElseThrow { NotFoundException("Post with id $id not found") }
        return PostMapper.INSTANCE.postToPostDto(post)
    }

    fun getAllPosts(): List<PostDto> =
        repository.findAll().map { PostMapper.INSTANCE.postToPostDto(it) }

    fun deletePost(id: Long) {
        val post = repository.findById(id).orElseThrow { NotFoundException("Post with id $id not found") }
        repository.delete(post)
    }

}