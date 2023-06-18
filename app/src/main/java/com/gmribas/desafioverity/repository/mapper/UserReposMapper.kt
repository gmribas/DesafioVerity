package com.gmribas.desafioverity.repository.mapper

import com.gmribas.desafioverity.domain.UserRepoDomain
import com.gmribas.desafioverity.network.dto.UserRepoDTO

interface UserReposMapper {

    fun dtoToDomain(dto: UserRepoDTO): UserRepoDomain

    fun dtoToDomainList(dtos: List<UserRepoDTO>): List<UserRepoDomain>
}

class UserReposMapperImpl(private val userMapper: UserMapper) : UserReposMapper {

    override fun dtoToDomain(dto: UserRepoDTO): UserRepoDomain {
        return UserRepoDomain(
            id = dto.id,
            nodeID = dto.nodeID,
            name = dto.name,
            fullName = dto.fullName,
            private = dto.private,
            owner = userMapper.dtoToDomain(dto.owner)
        )
    }

    override fun dtoToDomainList(dtos: List<UserRepoDTO>): List<UserRepoDomain> {
        return dtos.map { dtoToDomain(it) }
    }
}