package com.gmribas.desafioverity.presentation.screens.userdetails

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.gmribas.desafioverity.domain.usecase.GetUserDetailsUseCase
import com.gmribas.desafioverity.domain.usecase.GetUserReposUseCase
import com.gmribas.desafioverity.presentation.common.BaseViewModel
import com.gmribas.desafioverity.presentation.common.UiState
import com.gmribas.desafioverity.presentation.mapper.UserDetailsUIMapperImpl
import com.gmribas.desafioverity.presentation.mapper.UserReposUIMapperImpl
import com.gmribas.desafioverity.presentation.model.UserDetailsUIModel
import com.gmribas.desafioverity.presentation.model.UserRepoUIModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class UserDetailsViewModel(
    private val savedStateHandle: SavedStateHandle,
    private val detailsUseCase: GetUserDetailsUseCase,
    private val reposUseCase: GetUserReposUseCase,
    private val detailsMapper: UserDetailsUIMapperImpl,
    private val reposMapper: UserReposUIMapperImpl
): BaseViewModel<UserDetailsUIModel>() {


    private val _reposState: MutableStateFlow<UiState<List<UserRepoUIModel>>> by lazy {
        MutableStateFlow(UiState.Default)
    }

    val reposState: StateFlow<UiState<List<UserRepoUIModel>>> = _reposState.asStateFlow()

    override fun onResume(owner: LifecycleOwner) {
        submitState(UiState.Loading)

        val userName = savedStateHandle.get<String>("userName")

        if (userName.isNullOrBlank()) {
            submitState(UiState.Error("userName is empty"))
            return
        }

        getDetails(userName)

        getRepos(userName)
    }

    private fun getDetails(userName: String) {
        viewModelScope.launch(errorHandler) {
            detailsUseCase.execute(GetUserDetailsUseCase.Request(userName))
                .map { detailsMapper.convert(it) }
                .collect { submitState(it) }
        }
    }

    private fun getRepos(userName: String) {
        viewModelScope.launch(errorHandler) {
            reposUseCase.execute(GetUserReposUseCase.Request(userName))
                .map { reposMapper.convert(it) }
                .collect { _reposState.value = it }
        }
    }
}