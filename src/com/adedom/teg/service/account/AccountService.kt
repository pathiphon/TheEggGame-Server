package com.adedom.teg.service.account

import com.adedom.teg.controller.account.model.StateRequest
import com.adedom.teg.request.account.ChangePasswordRequest
import com.adedom.teg.request.account.ChangeProfileRequest
import com.adedom.teg.response.BaseResponse
import com.adedom.teg.response.PlayerResponse
import io.ktor.http.content.*

interface AccountService {

    suspend fun changeImageProfile(playerId: String, multiPartData: MultiPartData): BaseResponse

    fun fetchPlayerInfo(playerId: String?): PlayerResponse

    fun playerState(playerId: String?, stateRequest: StateRequest): BaseResponse

    fun changePassword(playerId: String, changePasswordRequest: ChangePasswordRequest): BaseResponse

    fun changeProfile(playerId: String, changeProfileRequest: ChangeProfileRequest): BaseResponse

}
