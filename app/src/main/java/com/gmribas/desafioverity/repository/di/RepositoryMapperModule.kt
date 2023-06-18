package com.gmribas.desafioverity.repository.di

import com.gmribas.desafioverity.repository.mapper.UserDetailsMapper
import com.gmribas.desafioverity.repository.mapper.UserDetailsMapperImpl
import com.gmribas.desafioverity.repository.mapper.UserMapper
import com.gmribas.desafioverity.repository.mapper.UserMapperImpl
import com.gmribas.desafioverity.repository.mapper.UserReposMapper
import com.gmribas.desafioverity.repository.mapper.UserReposMapperImpl
import org.koin.dsl.module

val repositoryMapperModule = module {

    factory<UserMapper> { UserMapperImpl() }

    factory<UserDetailsMapper> { UserDetailsMapperImpl() }

    factory<UserReposMapper> { UserReposMapperImpl(get()) }
}