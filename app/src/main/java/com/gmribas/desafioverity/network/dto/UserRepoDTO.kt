package com.gmribas.desafioverity.network.dto

import com.google.gson.annotations.SerializedName

data class UserRepoDTO (
    val id: Long,

    @SerializedName("node_id")
    val nodeID: String,

    val name: String,

    @SerializedName("full_name")
    val fullName: String,

    val private: Boolean,

    val owner: UserDTO
)