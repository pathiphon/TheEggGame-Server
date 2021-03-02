package com.adedom.teg.data.map

import com.adedom.teg.data.database.ItemCollections
import com.adedom.teg.data.database.LogActives
import com.adedom.teg.data.database.MultiCollections
import com.adedom.teg.data.models.ItemCollectionDb
import com.adedom.teg.data.models.LogActiveDb
import com.adedom.teg.data.models.MultiCollectionDb
import org.jetbrains.exposed.sql.ResultRow

internal class MapperImpl : Mapper {

    override fun itemCollection(row: ResultRow): ItemCollectionDb {
        return ItemCollectionDb(
            collectionId = row[ItemCollections.collectionId],
            playerId = row[ItemCollections.playerId],
            itemId = row[ItemCollections.itemId],
            qty = row[ItemCollections.qty],
            latitude = row[ItemCollections.latitude],
            longitude = row[ItemCollections.longitude],
            dateTime = row[ItemCollections.dateTime],
            mode = row[ItemCollections.mode],
        )
    }

    override fun logActive(row: ResultRow): LogActiveDb {
        return LogActiveDb(
            logId = row[LogActives.logId],
            playerId = row[LogActives.playerId],
            dateTimeIn = row[LogActives.dateTimeIn],
            dateTimeOut = row[LogActives.dateTimeOut],
        )
    }

    override fun multiCollection(row: ResultRow): MultiCollectionDb {
        return MultiCollectionDb(
            collectionId = row[MultiCollections.collectionId],
            roomNo = row[MultiCollections.roomNo],
            playerId = row[MultiCollections.playerId],
            team = row[MultiCollections.team],
            latitude = row[MultiCollections.latitude],
            longitude = row[MultiCollections.longitude],
            dateTime = row[MultiCollections.dateTime],
        )
    }

}