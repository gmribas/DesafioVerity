package com.gmribas.desafioverity.domain.usecase

import com.gmribas.desafioverity.domain.common.CommonUseCase
import com.gmribas.desafioverity.domain.model.UserRepoDomain
import com.gmribas.desafioverity.repository.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetUserReposUseCase(private val repository: UserRepository): CommonUseCase<GetUserReposUseCase.Request, GetUserReposUseCase.Response>() {

    data class Request(val userName: String) : CommonUseCase.Request

    data class Response(val response: List<UserRepoDomain>) : CommonUseCase.Response

    override suspend fun process(request: Request): Flow<Response> {
        return flow { emit(Response(repository.getUserRepos(request.userName))) }
    }
}