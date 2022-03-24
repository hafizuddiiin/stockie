package com.appfiz.stockie.data.service

import com.appfiz.stockie.data.response.market.GetPopularWatchlist
import io.ktor.client.*
import io.ktor.client.request.*

class YhApiService(private val httpClient: HttpClient) {

    suspend fun getPopularWatchlistResponse() : GetPopularWatchlist = httpClient.get(
        GET_POPULAR_WATCHLIST
    )
}