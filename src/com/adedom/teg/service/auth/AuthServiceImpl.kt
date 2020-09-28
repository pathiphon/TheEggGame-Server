package com.adedom.teg.service.auth

import com.adedom.teg.controller.auth.model.SignUpRequest
import com.adedom.teg.controller.auth.model.SignUpResponse
import com.adedom.teg.repositories.TegRepository
import com.adedom.teg.request.auth.SignInRequest
import com.adedom.teg.response.SignInResponse
import com.adedom.teg.util.validateIsNullOrBlank

class AuthServiceImpl(private val repository: TegRepository) : AuthService {

    override fun signIn(signInRequest: SignInRequest): SignInResponse {
        return SignInResponse()
    }

    override fun signUp(signUpRequest: SignUpRequest): SignUpResponse {
        val response = SignUpResponse()
        val (username, password, name, gender, birthdate) = signUpRequest

        val message: String = when {
            username.isNullOrBlank() -> signUpRequest::username.name.validateIsNullOrBlank()
            password.isNullOrBlank() -> signUpRequest::password.name.validateIsNullOrBlank()
            name.isNullOrBlank() -> signUpRequest::name.name.validateIsNullOrBlank()
            gender.isNullOrBlank() -> signUpRequest::gender.name.validateIsNullOrBlank()
            birthdate == null -> signUpRequest::birthdate.name.validateIsNullOrBlank()
            else -> {
                response.success = true
                "Service success $signUpRequest"
            }
        }

        response.message = message
        return response
    }

}