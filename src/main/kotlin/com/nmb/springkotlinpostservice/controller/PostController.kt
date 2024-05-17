package com.nmb.springkotlinpostservice.controller

import com.nmb.springkotlinpostservice.dto.PostDto
import com.nmb.springkotlinpostservice.service.PostService
import org.springframework.http.HttpStatus
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/posts")
class PostController(
    private val postService: PostService
) {
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createPost(@RequestBody postDTO: PostDto) {
        val username = SecurityContextHolder.getContext().authentication.name
        postService.createPost(username, postDTO)
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun getPost(@PathVariable id: Long): PostDto =
        postService.getPost(id)
}