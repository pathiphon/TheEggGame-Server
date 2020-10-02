package com.adedom.teg.http.models.request

data class LatlngRequest(
    val roomNo: String? = null,
    val playerId: String? = null,
    val latitude: Double? = null,
    val longitude: Double? = null
)