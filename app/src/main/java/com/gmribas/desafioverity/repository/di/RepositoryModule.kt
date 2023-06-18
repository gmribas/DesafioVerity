package com.gmribas.desafioverity.repository.di

import com.gmribas.desafioverity.repository.repository.UserRepository
import com.gmribas.desafioverity.repository.repository.UserRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {
    single<UserRepository> { UserRepositoryImpl(get()) }
}