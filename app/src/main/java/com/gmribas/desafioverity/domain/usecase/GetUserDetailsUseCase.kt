package com.gmribas.desafioverity.domain.usecase

import com.gmribas.desafioverity.domain.common.CommonUseCase
import com.gmribas.desafioverity.domain.model.UserDetailsDomain
import com.gmribas.desafioverity.repository.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetUserDetailsUseCase(private val repository: UserRepository): CommonUseCase<GetUserDetailsUseCase.Request, GetUserDetailsUseCase.Response>() {

    data class Request(val userName: String) : CommonUseCase.Request

    data class Response(val response: UserDetailsDomain) : CommonUseCase.Response

    override suspend fun process(request: Request): Flow<Response> {
        return flow { repository.getUserDetails(request.userName) }
    }
}