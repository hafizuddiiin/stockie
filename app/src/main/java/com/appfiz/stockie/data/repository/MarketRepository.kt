package com.appfiz.stockie.data.repository

import com.appfiz.stockie.data.response.market.GetPopularWatchlist
import com.appfiz.stockie.data.response.Response

interface MarketRepository {

    suspend fun getPopularWatchlistResponse(): Response<GetPopularWatchlist>
}