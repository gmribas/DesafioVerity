package com.gmribas.desafioverity.presentation.mapper

import com.gmribas.desafioverity.domain.model.UserDetailsDomain
import com.gmribas.desafioverity.domain.usecase.GetUserDetailsUseCase
import com.gmribas.desafioverity.presentation.model.UserDetailsUIModel
import org.junit.Assert
import org.junit.Test

class UserDetailsUIMapperImplTest {

    private val mapper = UserDetailsUIMapperImpl()

    @Test
    fun convertSuccess() {
        val detailsDomain = UserDetailsDomain(
            login = "login",
            id = 0L,
            nodeID = "nodeID",
            avatarURL = "avatarURL",
            gravatarID = "gravatarID",
            url = "url",
            htmlURL = "htmlURL",
            followersURL = "followersURL",
            followingURL = "followingURL",
            gistsURL = "gistsURL",
            starredURL = "starredURL",
            subscriptionsURL = "subscriptionsURL",
            organizationsURL = "organizationsURL",
            reposURL = "reposURL",
            eventsURL = "eventsURL",
            receivedEventsURL = "receivedEventsURL",
            type = "type",
            siteAdmin = false,
            name = "name",
            company = "company",
            blog = "blog",
            location = "location",
            email = "email",
            twitterUsername = "twitterUsername",
            publicRepos = 0L,
            publicGists = 0L,
            followers = 0L,
            following = 0L,
            createdAt = "createdAt",
            updatedAt = "updatedAt"
        )

        val detailsUi = UserDetailsUIModel(
            login = "login",
            id = 0L,
            nodeID = "nodeID",
            avatarURL = "avatarURL",
            gravatarID = "gravatarID",
            url = "url",
            htmlURL = "htmlURL",
            followersURL = "followersURL",
            followingURL = "followingURL",
            gistsURL = "gistsURL",
            starredURL = "starredURL",
            subscriptionsURL = "subscriptionsURL",
            organizationsURL = "organizationsURL",
            reposURL = "reposURL",
            eventsURL = "eventsURL",
            receivedEventsURL = "receivedEventsURL",
            type = "type",
            siteAdmin = false,
            name = "name",
            company = "company",
            blog = "blog",
            location = "location",
            email = "email",
            twitterUsername = "twitterUsername",
            publicRepos = 0L,
            publicGists = 0L,
            followers = 0L,
            following = 0L,
            createdAt = "createdAt",
            updatedAt = "updatedAt"
        )

        val response = GetUserDetailsUseCase.Response(detailsDomain)

        val result = mapper.convertSuccess(response)

        Assert.assertEquals(detailsUi, result)
    }
}