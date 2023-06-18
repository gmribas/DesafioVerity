package com.gmribas.desafioverity.repository

import com.gmribas.desafioverity.domain.UserDetailsDomain
import com.gmribas.desafioverity.domain.UserDomain
import com.gmribas.desafioverity.domain.UserRepoDomain

interface UserSource {

    suspend fun getUsers(): List<UserDomain>

    suspend fun getUserDetails(userName: String): UserDetailsDomain

    suspend fun getUserRepos(userName: String): List<UserRepoDomain>
}