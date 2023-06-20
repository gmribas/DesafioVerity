package com.gmribas.desafioverity.presentation.model

data class UserRepoUIModel(
    val id: Long,
    val nodeID: String,
    val name: String,
    val fullName: String,
    val private: Boolean,
    val owner: UserUIModel
)