package com.gmribas.desafioverity.repository.di

import com.gmribas.desafioverity.repository.source.UserSource
import com.gmribas.desafioverity.repository.source.UserSourceImpl
import org.koin.dsl.module

val sourceModule = module {
    factory<UserSource> { UserSourceImpl(get(), get(), get(), get()) }
}