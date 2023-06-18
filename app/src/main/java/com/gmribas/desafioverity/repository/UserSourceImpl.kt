package com.gmribas.desafioverity.repository

import com.gmribas.desafioverity.domain.UserDetailsDomain
import com.gmribas.desafioverity.domain.UserDomain
import com.gmribas.desafioverity.domain.UserRepoDomain
import com.gmribas.desafioverity.network.ApiService
import com.gmribas.desafioverity.repository.mapper.UserDetailsMapper
import com.gmribas.desafioverity.repository.mapper.UserMapper
import com.gmribas.desafioverity.repository.mapper.UserReposMapper

class UserSourceImpl(
    private val service: ApiService,
    private val userMapper: UserMapper,
    private val userDetailsMapper: UserDetailsMapper,
    private val userReposMapper: UserReposMapper,
): UserSource {

    override suspend fun getUsers(): List<UserDomain> {
        return userMapper.dtoToDomainList(service.getUsers())
    }

    override suspend fun getUserDetails(userName: String): UserDetailsDomain {
        return userDetailsMapper.dtoToDomain(service.getUserDetails(userName))
    }

    override suspend fun getUserRepos(userName: String): List<UserRepoDomain> {
        return userReposMapper.dtoToDomainList(service.getUserRepos(userName))
    }
}