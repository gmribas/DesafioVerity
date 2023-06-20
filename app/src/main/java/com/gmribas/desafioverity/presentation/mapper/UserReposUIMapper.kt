package com.gmribas.desafioverity.presentation.mapper

import com.gmribas.desafioverity.domain.model.UserRepoDomain
import com.gmribas.desafioverity.domain.usecase.GetUserReposUseCase
import com.gmribas.desafioverity.presentation.common.CommonResultConverter
import com.gmribas.desafioverity.presentation.model.UserRepoUIModel

class UserReposUIMapperImpl(private val userUIMapperImpl: UserUIMapperImpl) :
    CommonResultConverter<GetUserReposUseCase.Response, List<UserRepoUIModel>>() {

    override fun convertSuccess(data: GetUserReposUseCase.Response): List<UserRepoUIModel> {
        return data.response.map { it.toUiModel() }
    }

    private fun UserRepoDomain.toUiModel(): UserRepoUIModel {
        return UserRepoUIModel(
            id = id,
            nodeID = nodeID,
            name = name,
            fullName = fullName,
            private = private,
            owner = userUIMapperImpl.domainToUiModel(this.owner)
        )
    }
}