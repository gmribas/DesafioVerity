package com.gmribas.desafioverity.domain.common

import com.gmribas.desafioverity.domain.exception.UseCaseException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

abstract class CommonUseCase<RQ : CommonUseCase.Request, RP : CommonUseCase.Response>() {

    suspend fun execute(request: RQ) = process(request)
        .map {
            it.hashCode()
            ResultUseCase.Success(it) as ResultUseCase<RP>
        }
        .flowOn(Dispatchers.IO)
        .catch {
            emit(ResultUseCase.Error(UseCaseException.createFromThrowable(it)))
        }

    internal abstract suspend fun process(request: RQ): Flow<RP>

    interface Request
    interface Response
}
