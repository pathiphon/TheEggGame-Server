package com.adedom.teg.service.auth

import com.adedom.teg.controller.auth.model.SignUpRequest
import com.adedom.teg.controller.auth.model.SignUpResponse
import com.adedom.teg.repositories.TegRepository
import com.adedom.teg.request.auth.SignInRequest
import com.adedom.teg.response.SignInResponse
import com.adedom.teg.util.*

class AuthServiceImpl(private val repository: TegRepository) : AuthService {

    override fun signIn(signInRequest: SignInRequest): SignInResponse {
        return SignInResponse()
    }

    override fun signUp(signUpRequest: SignUpRequest): SignUpResponse {
        val response = SignUpResponse()
        val (username, password, name, gender, birthdate) = signUpRequest

        val message: String = when {
            // validate Null Or Blank
            username.isNullOrBlank() -> signUpRequest::username.name.validateIsNullOrBlank()
            password.isNullOrBlank() -> signUpRequest::password.name.validateIsNullOrBlank()
            name.isNullOrBlank() -> signUpRequest::name.name.validateIsNullOrBlank()
            gender.isNullOrBlank() -> signUpRequest::gender.name.validateIsNullOrBlank()
            birthdate.isNullOrBlank() -> signUpRequest::birthdate.name.validateIsNullOrBlank()

            // validate values of variable
            username.length < TegConstant.MIN_USERNAME -> signUpRequest::username.name.validateGrateEq(TegConstant.MIN_USERNAME)
            password.length < TegConstant.MIN_PASSWORD -> signUpRequest::password.name.validateGrateEq(TegConstant.MIN_PASSWORD)
            !gender.validateGender() -> signUpRequest::gender.name.toMessageGender()
            // TODO: 28/09/2563 validate birthdate

            // validate database
            repository.isUsernameRepeat(username) -> signUpRequest::username.name.toMessageRepeat(username)
            repository.isNameRepeat(name) -> signUpRequest::name.name.toMessageRepeat(name)

            // execute
            else -> {
                val signUp = repository.signUp(signUpRequest)
                response.success = signUp.success
                response.accessToken = signUp.accessToken
                "Service success"
            }
        }

        response.message = message
        return response
    }

}
