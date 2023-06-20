package com.gmribas.desafioverity.presentation.di

import com.gmribas.desafioverity.presentation.mapper.UserDetailsUIMapperImpl
import com.gmribas.desafioverity.presentation.mapper.UserReposUIMapperImpl
import com.gmribas.desafioverity.presentation.mapper.UserUIMapperImpl
import org.koin.dsl.module

val mapperModule = module {

    single { UserUIMapperImpl() }

    single { UserDetailsUIMapperImpl() }

    single { UserReposUIMapperImpl(get()) }
}