package com.adedom.teg.route

import com.adedom.teg.request.PostItemCollection
import com.adedom.teg.response.BackpackResponse
import com.adedom.teg.response.BaseResponse
import com.adedom.teg.transaction.DatabaseTransaction
import com.adedom.teg.util.validateEmpty
import com.adedom.teg.util.validateIncorrect
import com.adedom.teg.util.validateLessEqZero
import com.adedom.teg.util.validateNotFound
import io.ktor.application.call
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.routing.Route
import io.ktor.routing.get
import io.ktor.routing.post
import io.ktor.routing.route

fun Route.itemCollection() {

    route("item-collection") {
        val playerIdKey = "player_id"
        get("fetch-backpack{$playerIdKey}") {
            val response = BackpackResponse()
            val playerId = call.parameters[playerIdKey]
            val message = when {
                playerId.isNullOrBlank() -> playerIdKey.validateEmpty()
                playerId.toInt() <= 0 -> playerIdKey.validateIncorrect()
                DatabaseTransaction.getCountPlayer(playerId.toInt()) == 0 -> playerIdKey.validateNotFound()

                else -> {
                    val (egg, eggI, eggII, eggIII) = DatabaseTransaction.getBackpack(playerId.toInt())
                    response.egg = egg
                    response.eggI = eggI
                    response.eggII = eggII
                    response.eggIII = eggIII
                    response.success = true
                    "Fetch backpack success"
                }
            }
            response.message = message
            call.respond(response)
        }

        post("/") {
            val response = BaseResponse()
            val (playerId, itemId, qty, latitude, longitude) = call.receive<PostItemCollection>()
            val message = when {
                playerId == null -> PostItemCollection::playerId.name.validateEmpty()
                playerId <= 0 -> PostItemCollection::playerId.name.validateLessEqZero()
                DatabaseTransaction.getCountPlayer(playerId) == 0 -> PostItemCollection::playerId.name.validateNotFound()

                itemId == null -> PostItemCollection::itemId.name.validateEmpty()
                itemId <= 0 || itemId > 4 -> PostItemCollection::itemId.name.validateIncorrect()

                qty == null -> PostItemCollection::qty.name.validateEmpty()
                qty <= 0 -> PostItemCollection::qty.name.validateIncorrect()

                latitude == null -> PostItemCollection::latitude.name.validateEmpty()

                longitude == null -> PostItemCollection::longitude.name.validateEmpty()

                else -> {
                    DatabaseTransaction.postItemCollection(
                        postItemCollection = PostItemCollection(
                            playerId = playerId,
                            itemId = itemId,
                            qty = qty,
                            latitude = latitude,
                            longitude = longitude
                        )
                    )
                    response.success = true
                    "Post item collection success"
                }
            }
            response.message = message
            call.respond(response)
        }
    }

}