package com.gmribas.desafioverity.presentation.di

import com.gmribas.desafioverity.presentation.UserListViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val viewModelModule = module {

    viewModelOf(::UserListViewModel)
}