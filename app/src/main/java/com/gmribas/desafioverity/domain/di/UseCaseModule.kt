package com.gmribas.desafioverity.domain.di

import com.gmribas.desafioverity.domain.usecase.GetUserDetailsUseCase
import com.gmribas.desafioverity.domain.usecase.GetUserReposUseCase
import com.gmribas.desafioverity.domain.usecase.GetUsersUseCase
import org.koin.dsl.module

val useCaseModule = module {

    single { GetUsersUseCase(get()) }

    single { GetUserReposUseCase(get()) }

    single { GetUserDetailsUseCase(get()) }

}