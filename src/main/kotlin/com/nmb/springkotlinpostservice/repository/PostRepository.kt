package com.nmb.springkotlinpostservice.repository

import com.nmb.springkotlinpostservice.entity.Post
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PostRepository : JpaRepository<Post, Long> {}