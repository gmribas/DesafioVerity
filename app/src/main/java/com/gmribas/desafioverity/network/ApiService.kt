package com.gmribas.desafioverity.network

import com.gmribas.desafioverity.network.dto.UserDTO
import com.gmribas.desafioverity.network.dto.UserDetailsDTO
import com.gmribas.desafioverity.network.dto.UserRepoDTO
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("users")
    suspend fun getUsers(): List<UserDTO>

    @GET("users/{userName}")
    suspend fun getUserDetails(@Path("userName") userName: String): UserDetailsDTO

    @GET("users/{userName}/repos")
    suspend fun getUserRepos(@Path("userName") userName: String): List<UserRepoDTO>
}