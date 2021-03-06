package com.adedom.teg.models.request

import io.ktor.locations.*

@KtorExperimentalLocationsAPI
@Location("/api/auth/sign-in")
data class SignInRequest(
    val username: String? = null,
    val password: String? = null
)
