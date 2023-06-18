package com.gmribas.desafioverity.repository.source

import com.gmribas.desafioverity.domain.model.UserDetailsDomain
import com.gmribas.desafioverity.domain.model.UserDomain
import com.gmribas.desafioverity.domain.model.UserRepoDomain

interface UserSource {

    suspend fun getUsers(): List<UserDomain>

    suspend fun getUserDetails(userName: String): UserDetailsDomain

    suspend fun getUserRepos(userName: String): List<UserRepoDomain>
}