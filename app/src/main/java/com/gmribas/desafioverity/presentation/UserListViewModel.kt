package com.gmribas.desafioverity.presentation

import androidx.lifecycle.viewModelScope
import com.gmribas.desafioverity.domain.usecase.GetUsersUseCase
import com.gmribas.desafioverity.presentation.common.BaseViewModel
import com.gmribas.desafioverity.presentation.mapper.UserUIMapperImpl
import com.gmribas.desafioverity.presentation.model.UserUIModel
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class UserListViewModel(
    private val useCase: GetUsersUseCase,
    private val mapper: UserUIMapperImpl
): BaseViewModel<List<UserUIModel>>() {


    fun getUserList() {
        viewModelScope.launch(errorHandler) {
            useCase.execute(GetUsersUseCase.Request)
                .map { mapper.convert(it) }
                .collect {
                    submitState(it)
                }
        }
    }
}