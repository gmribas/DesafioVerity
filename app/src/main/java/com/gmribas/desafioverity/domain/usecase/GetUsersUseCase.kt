package com.gmribas.desafioverity.domain.usecase

import com.gmribas.desafioverity.domain.common.CommonUseCase
import com.gmribas.desafioverity.domain.model.UserDomain
import com.gmribas.desafioverity.repository.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetUsersUseCase(private val repository: UserRepository): CommonUseCase<GetUsersUseCase.Request, GetUsersUseCase.Response>() {

    object Request : CommonUseCase.Request

    data class Response(val response: List<UserDomain>) : CommonUseCase.Response

    override suspend fun process(request: Request): Flow<Response> {
        return flow { emit(Response(repository.getUsers())) }
    }
}