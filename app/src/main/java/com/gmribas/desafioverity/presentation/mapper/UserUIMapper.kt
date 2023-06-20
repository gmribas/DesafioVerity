package com.gmribas.desafioverity.presentation.mapper

import com.gmribas.desafioverity.domain.model.UserDomain
import com.gmribas.desafioverity.domain.usecase.GetUsersUseCase
import com.gmribas.desafioverity.presentation.common.CommonResultConverter
import com.gmribas.desafioverity.presentation.model.UserUIModel

class UserUIMapperImpl : CommonResultConverter<GetUsersUseCase.Response, List<UserUIModel>>() {

    override fun convertSuccess(data: GetUsersUseCase.Response): List<UserUIModel> {
        return data.response.map { it.toUiModel() }
    }

    fun domainToUiModel(domain: UserDomain): UserUIModel = domain.toUiModel()

    private fun UserDomain.toUiModel(): UserUIModel {
        return UserUIModel(
            login = login,
            id = id,
            avatarURL = avatarURL
        )
    }
}