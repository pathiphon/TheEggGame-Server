package com.adedom.teg.business.single

import com.adedom.teg.models.request.ItemCollectionRequest
import com.adedom.teg.models.response.BackpackResponse
import com.adedom.teg.models.response.BaseResponse
import com.adedom.teg.models.websocket.SingleItemOutgoing
import io.ktor.locations.*

@KtorExperimentalLocationsAPI
interface SingleService {

    fun fetchItemCollection(playerId: String?): BackpackResponse

    fun itemCollection(playerId: String?, itemCollectionRequest: ItemCollectionRequest): BaseResponse

    fun singleItem(accessToken: String): SingleItemOutgoing

}
