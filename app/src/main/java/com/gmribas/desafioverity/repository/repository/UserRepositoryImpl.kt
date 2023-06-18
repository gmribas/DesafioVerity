package com.gmribas.desafioverity.repository.repository

import com.gmribas.desafioverity.domain.model.UserDetailsDomain
import com.gmribas.desafioverity.domain.model.UserDomain
import com.gmribas.desafioverity.domain.model.UserRepoDomain
import com.gmribas.desafioverity.repository.source.UserSource

class UserRepositoryImpl(private val source: UserSource): UserRepository {

    override suspend fun getUsers(): List<UserDomain> = source.getUsers()

    override suspend fun getUserDetails(userName: String): UserDetailsDomain = source.getUserDetails(userName)

    override suspend fun getUserRepos(userName: String): List<UserRepoDomain> = source.getUserRepos(userName)
}