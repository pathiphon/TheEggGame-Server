package com.adedom.teg.di

import com.adedom.teg.service.auth.AuthService
import com.adedom.teg.service.auth.AuthServiceImpl
import com.adedom.teg.service.teg.TegService
import com.adedom.teg.service.teg.TegServiceImpl
import org.koin.dsl.module

private val businessModule = module {

    single<AuthService> { AuthServiceImpl(get()) }
    single<TegService> { TegServiceImpl(get()) }

}

val getBusinessModule = businessModule