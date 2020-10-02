package com.adedom.teg.business.service.application

import com.adedom.teg.business.business.TegBusiness
import com.adedom.teg.http.models.request.RankPlayersRequest
import com.adedom.teg.data.repositories.TegRepository
import com.adedom.teg.http.models.response.BaseResponse
import com.adedom.teg.http.models.response.RankPlayersResponse
import io.ktor.locations.*

@KtorExperimentalLocationsAPI
class ApplicationServiceImpl(
    private val repository: TegRepository,
    private val business: TegBusiness,
) : ApplicationService {

    override fun fetchRankPlayers(rankPlayersRequest: RankPlayersRequest): RankPlayersResponse {
        val response = RankPlayersResponse()
        val (_, search, limit) = rankPlayersRequest

        val message: String = when {
            // validate Null Or Blank
            search == null -> business.toMessageIsNullOrBlank(rankPlayersRequest::search)
            limit.isNullOrBlank() -> business.toMessageIsNullOrBlank(rankPlayersRequest::limit)
            limit.toIntOrNull() == null -> business.toMessageIsNullOrBlank(rankPlayersRequest::limit)

            // validate values of variable
            limit.toInt() <= 0 -> business.toMessageIncorrect(rankPlayersRequest::limit)
            !business.isValidateRankPlayer(limit.toInt()) -> business.toMessageIncorrect(rankPlayersRequest::limit)

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

    override fun logActiveOn(playerId: String?): BaseResponse {
        val response = BaseResponse()

        val message: String = when {
            // validate Null Or Blank
            playerId.isNullOrBlank() -> business.toMessageIsNullOrBlank(playerId)

            // validate values of variable

            // validate database

            // execute
            else -> {
                response.success = repository.logActiveOn(playerId)
                "Post log active success"
            }
        }

        response.message = message
        return response
    }

    override fun logActiveOff(playerId: String?): BaseResponse {
        val response = BaseResponse()

        val message: String = when {
            // validate Null Or Blank
            playerId.isNullOrBlank() -> business.toMessageIsNullOrBlank(playerId)

            // validate values of variable

            // validate database

            // execute
            else -> {
                response.success = repository.logActiveOff(playerId)
                "Patch log active success"
            }
        }

        response.message = message
        return response
    }

}