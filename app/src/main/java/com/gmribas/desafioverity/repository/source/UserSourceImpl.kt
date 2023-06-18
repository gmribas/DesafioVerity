package com.gmribas.desafioverity.repository.source

import com.gmribas.desafioverity.domain.model.UserDetailsDomain
import com.gmribas.desafioverity.domain.model.UserDomain
import com.gmribas.desafioverity.domain.model.UserRepoDomain
import com.gmribas.desafioverity.domain.exception.UseCaseException
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
        try {
            return userMapper.dtoToDomainList(service.getUsers())
        } catch (e: Throwable) {
            throw UseCaseException.createFromThrowable(e)
        }
    }

    override suspend fun getUserDetails(userName: String): UserDetailsDomain {
        try {
            return userDetailsMapper.dtoToDomain(service.getUserDetails(userName))
        } catch (e: Throwable) {
            throw UseCaseException.createFromThrowable(e)
        }
    }

    override suspend fun getUserRepos(userName: String): List<UserRepoDomain> {
        try {
            return userReposMapper.dtoToDomainList(service.getUserRepos(userName))
        } catch (e: Throwable) {
            throw UseCaseException.createFromThrowable(e)
        }
    }
}