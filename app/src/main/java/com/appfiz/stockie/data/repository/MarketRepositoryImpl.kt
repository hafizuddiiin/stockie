package com.appfiz.stockie.data.repository

import com.appfiz.stockie.data.service.YhApiService
import com.appfiz.stockie.data.response.market.GetPopularWatchlist
import com.appfiz.stockie.data.response.Response
import io.ktor.client.features.*

private const val TAG = "MarketRepositoryImpl"

class MarketRepositoryImpl(private val yhApiService: YhApiService): MarketRepository {

    override suspend fun getPopularWatchlistResponse(): Response<GetPopularWatchlist> {
        return try {
            val getPopularWatchlist = yhApiService.getPopularWatchlistResponse()
            Response.Success(getPopularWatchlist)
        } catch (e: ClientRequestException) {
            // 4xx
            Response.Error("errorMsg= ${e.response.status.description}")
        } catch (e: ServerResponseException) {
            // 5xx
            Response.Error("errorMsg= ${e.response.status.description}")
        }
    }

}