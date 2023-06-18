package com.gmribas.desafioverity.repository.mapper

import com.gmribas.desafioverity.domain.UserDetailsDomain
import com.gmribas.desafioverity.domain.UserDomain
import com.gmribas.desafioverity.network.dto.UserDTO
import com.gmribas.desafioverity.network.dto.UserDetailsDTO

interface UserDetailsMapper {

    fun dtoToDomain(dto: UserDetailsDTO): UserDetailsDomain
}

class UserDetailsMapperImpl : UserDetailsMapper {

    override fun dtoToDomain(dto: UserDetailsDTO): UserDetailsDomain {
        return UserDetailsDomain(
            login = dto.login,
            id = dto.id,
            nodeID = dto.nodeID,
            avatarURL = dto.avatarURL,
            gravatarID = dto.gravatarID,
            url = dto.url,
            htmlURL = dto.htmlURL,
            followersURL = dto.followersURL,
            followingURL = dto.followingURL,
            gistsURL = dto.gistsURL,
            starredURL = dto.starredURL,
            subscriptionsURL = dto.subscriptionsURL,
            organizationsURL = dto.organizationsURL,
            reposURL = dto.reposURL,
            eventsURL = dto.eventsURL,
            receivedEventsURL = dto.receivedEventsURL,
            type = dto.type,
            siteAdmin = dto.siteAdmin,
            name = dto.name,
            company = dto.company,
            blog = dto.blog,
            location = dto.location,
            email = dto.email,
            twitterUsername = dto.twitterUsername,
            publicRepos = dto.publicRepos,
            publicGists = dto.publicGists,
            followers = dto.followers,
            following = dto.following,
            createdAt = dto.createdAt,
            updatedAt = dto.updatedAt
        )
    }
}