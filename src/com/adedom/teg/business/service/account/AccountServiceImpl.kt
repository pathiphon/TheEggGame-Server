package com.adedom.teg.business.service.account

import com.adedom.teg.http.models.request.ChangePasswordRequest
import com.adedom.teg.http.models.request.ChangeProfileRequest
import com.adedom.teg.http.models.request.StateRequest
import com.adedom.teg.data.repositories.TegRepository
import com.adedom.teg.http.models.response.BaseResponse
import com.adedom.teg.http.models.response.PlayerResponse
import com.adedom.teg.refactor.GetConstant
import com.adedom.teg.business.business.TegBusiness
import com.adedom.teg.util.TegConstant
import io.ktor.locations.*

@KtorExperimentalLocationsAPI
class AccountServiceImpl(
    private val repository: TegRepository,
    private val business: TegBusiness,
) : AccountService {

    override fun changeImageProfile(playerId: String?, imageName: String?): BaseResponse {
        val response = BaseResponse()

        val message: String? = when {
            // validate Null Or Blank
            playerId.isNullOrBlank() -> business.toMessageIsNullOrBlank(playerId)
            imageName.isNullOrBlank() -> "Not found image file [${GetConstant.IMAGE_FILE}]"

            // validate values of variable

            // validate database

            // execute
            else -> {
                response.success = repository.changeImageProfile(playerId, imageName)
                "Put change image profile success"
            }
        }

        response.message = message
        return response
    }

    override fun fetchPlayerInfo(playerId: String?): PlayerResponse {
        val response = PlayerResponse()

        val message: String = when {
            // validate Null Or Blank
            playerId.isNullOrBlank() -> business.toMessageIsNullOrBlank(playerId)

            // validate values of variable

            // validate database

            // execute
            else -> {
                response.success = true
                response.playerInfo = repository.fetchPlayerInfo(playerId)
                "Fetch player info success"
            }
        }

        response.message = message
        return response
    }

    override fun playerState(playerId: String?, stateRequest: StateRequest): BaseResponse {
        val response = BaseResponse()
        val (state) = stateRequest
        val message: String = when {
            // validate Null Or Blank
            playerId.isNullOrBlank() -> business.toMessageIsNullOrBlank(playerId)
            state.isNullOrBlank() -> business.toMessageIsNullOrBlank(stateRequest::state)

            // validate values of variable
            !business.isValidateState(state) -> business.toMessageIncorrect(stateRequest::state)

            // validate database

            // execute
            else -> {
                response.success = repository.playerState(playerId, stateRequest)
                "Patch state success"
            }
        }

        response.message = message
        return response
    }

    override fun changePassword(playerId: String?, changePasswordRequest: ChangePasswordRequest): BaseResponse {
        val response = BaseResponse()
        val (oldPassword, newPassword) = changePasswordRequest
        val message: String = when {
            // validate Null Or Blank
            playerId.isNullOrBlank() -> business.toMessageIsNullOrBlank(playerId)
            oldPassword.isNullOrBlank() -> business.toMessageIsNullOrBlank(changePasswordRequest::oldPassword)
            newPassword.isNullOrBlank() -> business.toMessageIsNullOrBlank(changePasswordRequest::newPassword)

            // validate values of variable
            oldPassword.length < TegConstant.MIN_PASSWORD ->
                business.validateGrateEq(changePasswordRequest::oldPassword, TegConstant.MIN_PASSWORD)
            newPassword.length < TegConstant.MIN_PASSWORD ->
                business.validateGrateEq(changePasswordRequest::newPassword, TegConstant.MIN_PASSWORD)

            // validate database
            repository.isValidateChangePassword(playerId, changePasswordRequest) -> "Password incorrect"

            // execute
            else -> {
                response.success = repository.changePassword(playerId, changePasswordRequest)
                "Put change password success"
            }
        }

        response.message = message
        return response
    }

    override fun changeProfile(playerId: String?, changeProfileRequest: ChangeProfileRequest): BaseResponse {
        val response = BaseResponse()
        val (name, gender, birthdate) = changeProfileRequest
        val message: String = when {
            // validate Null Or Blank
            playerId.isNullOrBlank() -> business.toMessageIsNullOrBlank(playerId)
            name.isNullOrBlank() -> business.toMessageIsNullOrBlank(changeProfileRequest::name)
            gender.isNullOrBlank() -> business.toMessageIsNullOrBlank(changeProfileRequest::gender)
            birthdate.isNullOrBlank() -> business.toMessageIsNullOrBlank(changeProfileRequest::birthdate)

            // validate values of variable
            !business.isValidateGender(gender) -> business.toMessageIncorrect(changeProfileRequest::gender)
            business.isValidateDateTime(birthdate) -> business.toMessageIncorrect(changeProfileRequest::birthdate)

            // validate database
            repository.isNameRepeat(name) -> business.toMessageRepeat(changeProfileRequest::name, name)

            // execute
            else -> {
                response.success = repository.changeProfile(playerId, changeProfileRequest)
                "Put change profile success"
            }
        }

        response.message = message
        return response
    }

}