package com.adedom.teg.service.application

import com.adedom.teg.controller.application.model.RankPlayersRequest
import com.adedom.teg.repositories.TegRepository
import com.adedom.teg.request.application.LogActiveRequest
import com.adedom.teg.response.BaseResponse
import com.adedom.teg.response.RankPlayersResponse
import com.adedom.teg.util.isValidateRankPlayer
import com.adedom.teg.util.toMessageIncorrect
import com.adedom.teg.util.toMessageIsNullOrBlank

class ApplicationServiceImpl(private val repository: TegRepository) : ApplicationService {

    override fun fetchRankPlayers(rankPlayersRequest: RankPlayersRequest): RankPlayersResponse {
        val response = RankPlayersResponse()
        val (_, search, limit) = rankPlayersRequest

        val message: String = when {
            // validate Null Or Blank
            search == null -> rankPlayersRequest::search.name.toMessageIsNullOrBlank()
            limit.isNullOrBlank() -> rankPlayersRequest::limit.name.toMessageIsNullOrBlank()
            limit.toIntOrNull() == null -> rankPlayersRequest::limit.name.toMessageIsNullOrBlank()

            // validate values of variable
            limit.toInt() <= 0 -> rankPlayersRequest::limit.name.toMessageIncorrect()
            !limit.toInt().isValidateRankPlayer() -> rankPlayersRequest::limit.name.toMessageIncorrect()

            // validate database

            // execute
            else -> {
                response.success = true
                response.rankPlayers = repository.fetchRankPlayers(rankPlayersRequest)
                "Fetch rank players success Hey"
            }
        }

        response.message = message
        return response
    }

    override fun postLogActive(playerId: String, logActiveRequest: LogActiveRequest): BaseResponse {
        return BaseResponse()
    }

}
