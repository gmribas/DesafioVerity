package com.gmribas.desafioverity.presentation.mapper

import com.gmribas.desafioverity.domain.model.UserDetailsDomain
import com.gmribas.desafioverity.domain.usecase.GetUserDetailsUseCase
import com.gmribas.desafioverity.presentation.common.CommonResultConverter
import com.gmribas.desafioverity.presentation.model.UserDetailsUIModel

class UserDetailsUIMapperImpl :
    CommonResultConverter<GetUserDetailsUseCase.Response, UserDetailsUIModel>() {

    override fun convertSuccess(data: GetUserDetailsUseCase.Response): UserDetailsUIModel {
        return data.response.toUiModel()
    }

    private fun UserDetailsDomain.toUiModel(): UserDetailsUIModel {
        return UserDetailsUIModel(
            login = login,
            id = id,
            nodeID = nodeID,
            avatarURL = avatarURL,
            gravatarID = gravatarID,
            url = url,
            htmlURL = htmlURL,
            followersURL = followersURL,
            followingURL = followingURL,
            gistsURL = gistsURL,
            starredURL = starredURL,
            subscriptionsURL = subscriptionsURL,
            organizationsURL = organizationsURL,
            reposURL = reposURL,
            eventsURL = eventsURL,
            receivedEventsURL = receivedEventsURL,
            type = type,
            siteAdmin = siteAdmin,
            name = name,
            company = company,
            blog = blog,
            location = location,
            email = email,
            twitterUsername = twitterUsername,
            publicRepos = publicRepos,
            publicGists = publicGists,
            followers = followers,
            following = following,
            createdAt = createdAt,
            updatedAt = updatedAt
        )
    }
}