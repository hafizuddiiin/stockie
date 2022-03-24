package com.appfiz.stockie.data

import com.appfiz.stockie.data.mock.getPopularWatchlist
import com.appfiz.stockie.data.repository.MarketRepository
import com.appfiz.stockie.data.response.Response
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`

class MarketRepositoryTest {

    @Mock
    lateinit var repository: MarketRepository

    @Before
    fun setup() {
        repository = Mockito.mock(MarketRepository::class.java)
    }

    @Test
    fun `get popular watchlist success`() {
        runBlocking {
            `when`(repository.getPopularWatchlistResponse())
                .thenReturn(Response.Success(getPopularWatchlist))

            assertThat(repository.getPopularWatchlistResponse()).isInstanceOf(Response.Success::class.java)
        }
    }

    @Test
    fun `get popular watchlist error`() {
        runBlocking {
            `when`(repository.getPopularWatchlistResponse())
                .thenReturn(Response.Error("errorMsg= server response exception"))

            assertThat(repository.getPopularWatchlistResponse()).isInstanceOf(Response.Error::class.java)
        }
    }


}