package com.adedom.teg.repositories

import com.adedom.teg.controller.auth.model.SignUpRequest
import com.adedom.teg.controller.auth.model.SignUpResponse
import com.adedom.teg.request.account.ChangePasswordRequest
import com.adedom.teg.request.account.ChangeProfileRequest
import com.adedom.teg.request.account.StateRequest
import com.adedom.teg.request.application.LogActiveRequest
import com.adedom.teg.request.application.RankPlayersRequest
import com.adedom.teg.request.auth.SignInRequest
import com.adedom.teg.request.single.ItemCollectionRequest
import com.adedom.teg.response.*
import io.ktor.http.content.*

interface TegRepository {

    fun postSignIn(signInRequest: SignInRequest): SignInResponse

    fun postSignUp(signUpRequest: SignUpRequest): SignUpResponse

    suspend fun changeImageProfile(playerId: Int, multiPartData: MultiPartData): BaseResponse

    fun fetchPlayerInfo(playerId: Int): PlayerResponse

    fun playerState(playerId: Int, stateRequest: StateRequest): BaseResponse

    fun changePassword(playerId: Int, changePasswordRequest: ChangePasswordRequest): BaseResponse

    fun changeProfile(playerId: Int, changeProfileRequest: ChangeProfileRequest): BaseResponse

    fun fetchRankPlayers(rankPlayersRequest: RankPlayersRequest): RankPlayersResponse

    fun postLogActive(playerId: Int, logActiveRequest: LogActiveRequest): BaseResponse

    fun fetchItemCollection(playerId: Int): BackpackResponse

    fun postItemCollection(playerId: Int, itemCollectionRequest: ItemCollectionRequest): BaseResponse

}
