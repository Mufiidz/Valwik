package id.my.mufidz.valwik.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import id.my.mufidz.valwik.data.network.ApiServices
import id.my.mufidz.valwik.data.network.ApiServicesImpl
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.plugins.observer.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
import timber.log.Timber
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private const val TIMEOUT = 10_000
    private const val LOGGING = "Network Request"
    const val VALORANT_API = "valorant_api"
    const val UNOFFICIAL_VALORANT_API = "unofficial_valorant_api"

    @Singleton
    @Provides
    fun provideBaseHttpClient(): HttpClient = HttpClient(Android) {
        install(ContentNegotiation) {
            json(
                Json {
                    prettyPrint = true
                    isLenient = true
                    ignoreUnknownKeys = true
                    coerceInputValues = true

                },ContentType.Application.Json
            )
            engine {
                connectTimeout = TIMEOUT
                socketTimeout = TIMEOUT
            }
        }
        install(Logging) {
            level = LogLevel.ALL
            logger = object : Logger {
                override fun log(message: String) {
                    Timber.tag(LOGGING).d(message)
                }
            }
        }
        install(DefaultRequest) { header(HttpHeaders.ContentType, ContentType.Application.Json) }
    }

    @Provides
    @Singleton
    @Named(VALORANT_API)
    fun provideHttpClientValApi(httpClient: HttpClient) : HttpClient = httpClient.config {
        defaultRequest { url("https://valorant-api.com/v1/") }
    }

    @Provides
    @Singleton
    @Named(UNOFFICIAL_VALORANT_API)
    fun provideHttpClientUnofficialValorantApi(httpClient: HttpClient) : HttpClient = httpClient.config {
        defaultRequest { url("https://api.henrikdev.xyz/valorant/v1/") }
    }

    @Provides
    @Singleton
    fun provideApiServices(apiServicesImpl: ApiServicesImpl) : ApiServices = apiServicesImpl
}