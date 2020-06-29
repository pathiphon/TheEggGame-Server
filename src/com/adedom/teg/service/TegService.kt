package com.adedom.teg.service

import com.adedom.teg.request.SignInRequest
import com.adedom.teg.request.SignUpRequest
import com.adedom.teg.util.jwt.PlayerPrincipal
import io.ktor.http.content.MultiPartData

interface TegService {

    fun signIn(signInRequest: SignInRequest): Pair<String, PlayerPrincipal?>

    fun signUp(signUpRequest: SignUpRequest): Pair<String, PlayerPrincipal?>

    suspend fun changeImageProfile(playerId: Int, multiPartData: MultiPartData)

}
