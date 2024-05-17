package com.nmb.springkotlinpostservice.entity

import jakarta.persistence.*

@Entity
@Table(name = "posts")
data class Post(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,

    @Column(name = "title")
    val title: String,

    @Column(name = "content")
    val content: String,

    // TODO: Check var or val
    @Column(name = "username")
    var username: String
)