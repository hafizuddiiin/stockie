package com.appfiz.stockie.data

import com.appfiz.stockie.data.mock.MockGetPopularWatchlist
import com.appfiz.stockie.data.service.YhApiService
import com.appfiz.stockie.di.appTestModule
import io.ktor.client.*
import io.ktor.client.engine.mock.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.http.*
import kotlinx.coroutines.runBlocking
import org.junit.*
import org.koin.core.context.stopKoin
import org.koin.core.parameter.parametersOf
import org.koin.test.KoinTest
import org.koin.test.KoinTestRule
import org.koin.test.inject
import kotlin.test.assertTrue

class YhApiServiceTest : KoinTest {

    @get:Rule
    val koinTestRule = KoinTestRule.create {
        modules(appTestModule)
    }

    private lateinit var httpClient: HttpClient

    private val yhApiService: YhApiService by inject { parametersOf(httpClient) }

    @Before
    fun setup() {
        val mockEngine = MockEngine {
            respond(
                content = MockGetPopularWatchlist.toString(),
                status = HttpStatusCode.OK,
                headers = headersOf(HttpHeaders.ContentType, "application/json")
            )
        }
        httpClient = HttpClient(mockEngine) {
            install(JsonFeature) {
                serializer = KotlinxSerializer(kotlinx.serialization.json.Json {
                    prettyPrint = true
                    isLenient = true
                    ignoreUnknownKeys = true
                })
            }
        }
    }

    @After
    fun after() {
        stopKoin()
    }

    @Test
    fun `get popular watchlist success`() {
        runBlocking {
            val getPopularWatchlist = yhApiService.getPopularWatchlistResponse()
            val result = getPopularWatchlist.finance?.result?.get(0)
            assertTrue(result?.total!! > 0)
            assertTrue(result.portfolios.isNotEmpty())
        }
    }

}