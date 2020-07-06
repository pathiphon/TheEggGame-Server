package com.adedom.teg.request.application

import io.ktor.locations.Location

@Location("/api/application/rank/{rank}")
data class RankPlayersRequest(
    val rank: String? = null,
    val search: String? = null,
    val limit: String? = null
)