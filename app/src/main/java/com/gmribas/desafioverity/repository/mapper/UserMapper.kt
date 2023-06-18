package com.gmribas.desafioverity.repository.mapper

import com.gmribas.desafioverity.domain.UserDomain
import com.gmribas.desafioverity.network.dto.UserDTO

interface UserMapper {

    fun dtoToDomain(dto: UserDTO): UserDomain

    fun dtoToDomainList(dtos: List<UserDTO>): List<UserDomain>
}

class UserMapperImpl: UserMapper {

    override fun dtoToDomain(dto: UserDTO): UserDomain = UserDomain(login = dto.login, id = dto.id, avatarURL = dto.avatarURL)

    override fun dtoToDomainList(dtos: List<UserDTO>): List<UserDomain> {
        return dtos.map { dtoToDomain(it) }
    }
}