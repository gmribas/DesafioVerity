package com.gmribas.desafioverity.presentation.di

import com.gmribas.desafioverity.presentation.mapper.UserUIMapperImpl
import org.koin.dsl.module

val mapperModule = module {

    single { UserUIMapperImpl() }
}