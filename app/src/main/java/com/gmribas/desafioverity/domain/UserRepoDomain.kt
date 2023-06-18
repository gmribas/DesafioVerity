package com.gmribas.desafioverity.domain

data class UserRepoDomain (
    val id: Long,
    val nodeID: String,
    val name: String,
    val fullName: String,
    val private: Boolean,
    val owner: UserDomain
)