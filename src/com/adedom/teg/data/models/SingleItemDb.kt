package com.adedom.teg.data.models

data class SingleItemDb(
    val singleId: Int,
    val itemTypeId: Int,
    val latitude: Double,
    val longitude: Double,
    val playerId: String?,
    val status: String,
    val dateTimeCreated: Long,
    val dateTimeUpdated: Long?,
)
