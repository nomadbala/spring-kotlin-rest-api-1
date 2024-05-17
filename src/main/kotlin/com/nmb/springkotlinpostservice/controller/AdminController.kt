package com.nmb.springkotlinpostservice.controller

import com.nmb.springkotlinpostservice.dto.PostDto
import com.nmb.springkotlinpostservice.service.PostService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/admin")
class AdminController(
    private val postService: PostService
) {
    @GetMapping("/posts")
    @ResponseStatus(HttpStatus.OK)
    fun getAllPosts(): List<PostDto> =
        postService.getAllPosts()

    @DeleteMapping("/posts/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deletePost(@PathVariable id: Long) =
        postService.deletePost(id)
}