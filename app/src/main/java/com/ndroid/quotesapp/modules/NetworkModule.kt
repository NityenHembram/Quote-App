package com.ndroid.quotesapp.modules

import com.ndroid.quotesapp.networks.ApiInterface
import com.ndroid.quotesapp.repo.NetworkRepo
import com.ndroid.quotesapp.repo.NetworkRepoImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.http.URLProtocol
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import javax.inject.Singleton


/**
 * Created by Nityen on 08-05-2025.
 */



@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    fun provideClient():HttpClient =
        HttpClient(Android){
            install(ContentNegotiation){
                json(Json {
                    ignoreUnknownKeys = true
                })
            }

            install(DefaultRequest){
                url {
                    host = ApiInterface.BASE_API
                    protocol = URLProtocol.HTTPS
                }
            }

            install(Logging){
                level = LogLevel.ALL
            }
        }

    @Provides
    fun provideNetworkRepo(client: HttpClient):NetworkRepo{
      return  NetworkRepoImpl(client)
    }


}