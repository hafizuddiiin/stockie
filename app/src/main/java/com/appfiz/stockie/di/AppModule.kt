package com.appfiz.stockie.di

import android.util.Log
import com.appfiz.stockie.BuildConfig
import com.appfiz.stockie.data.repository.MarketRepositoryImpl
import com.appfiz.stockie.data.service.YhApiService
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.features.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import io.ktor.client.features.observer.*
import io.ktor.client.request.*
import io.ktor.http.*
import org.koin.dsl.module
import io.ktor.client.engine.android.Android as Android

val repositoryModule = module {
    single { MarketRepositoryImpl(get()) }
}

val apiServiceModule = module {

    fun provideKtorClient() : HttpClient{
        return HttpClient(Android) {

            // config json serializer/deserializer
            install(JsonFeature) {
                serializer = KotlinxSerializer(kotlinx.serialization.json.Json {
                    prettyPrint = true
                    isLenient = true
                    ignoreUnknownKeys = true
                })
            }

            // define timeout period
            install(HttpTimeout) {
                requestTimeoutMillis = 15000L
                connectTimeoutMillis = 15000L
                socketTimeoutMillis = 15000L
            }

            // config logging to create HTTP request logs
            install(Logging) {
                logger = object : Logger {
                    override fun log(message: String) {
                        Log.v("KtorLogger", message)
                    }
                }
                level = LogLevel.ALL
            }

            // observer that record all states of response
            install(ResponseObserver) {
                onResponse { response ->
                    Log.d("KtorHttpStatus", "${response.status.value}")
                }
            }

            // set default values for each HTTP request
            install(DefaultRequest) {
                header(HttpHeaders.ContentType, ContentType.Application.Json)
                header("X-RapidAPI-Host", BuildConfig.yh_finance_rapid_api_host)
                header("X-RapidAPI-Key", BuildConfig.yh_finance_rapid_api_key)
            }

        }
    }
    single { YhApiService(provideKtorClient()) }
}

val appTestModule = module {
    single { client -> YhApiService(client.get()) }
}

val appModules = listOf(
            apiServiceModule,
            repositoryModule)
