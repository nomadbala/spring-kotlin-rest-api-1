package com.nmb.springkotlinpostservice.entity

import jakarta.persistence.*

@Entity
@Table(name = "roles")
data class Role(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,

    @Column(name = "name")
    var name: String
) {
    // TODO: Complete this method
    constructor(name: String): this(-1, name) {}
}