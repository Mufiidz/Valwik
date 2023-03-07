package id.my.mufidz.valwik.data.network

import id.my.mufidz.valwik.di.NetworkModule
import id.my.mufidz.valwik.models.*
import id.my.mufidz.valwik.models.agent.Agent
import id.my.mufidz.valwik.models.weapon.Weapon
import id.my.mufidz.valwik.models.weapon.WeaponSkin
import id.my.mufidz.valwik.utils.DataResult
import id.my.mufidz.valwik.utils.awaitResults
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*
import javax.inject.Inject
import javax.inject.Named

interface ApiServices {
    suspend fun getAgents(): DataResult<ValorantApiResponse<List<Agent>>>
    suspend fun getBundles(): DataResult<ValorantApiResponse<List<WeaponBundle>>>
    suspend fun getWeapons(): DataResult<ValorantApiResponse<List<Weapon>>>
    suspend fun getMaps(): DataResult<ValorantApiResponse<List<Maps>>>
    suspend fun getGameUpdates(): DataResult<ValorantApiResponse<List<News>>>
    suspend fun getWeaponSkins(): DataResult<ValorantApiResponse<List<WeaponSkin>>>
}

class ApiServicesImpl @Inject constructor(
    @Named(NetworkModule.VALORANT_API) private val valorantHttpClient: HttpClient,
    @Named(NetworkModule.UNOFFICIAL_VALORANT_API) private val httpClient: HttpClient,
) : ApiServices {

    companion object {
        private const val LANGUAGE = "language"
        private const val LANG_ID = "id-ID"
        private const val AGENTS = "agents"
        private const val BUNDLES = "bundles"
        private const val WEAPONS = "weapons"
        private const val MAPS = "maps"
        private const val NEWS = "website/en-us"
        private const val SKINS = "skins"
    }

    override suspend fun getAgents(): DataResult<ValorantApiResponse<List<Agent>>> =
        valorantHttpClient.get(AGENTS) {
            langParams(); parameter("isPlayableCharacter", true)
        }.awaitResults()

    override suspend fun getBundles(): DataResult<ValorantApiResponse<List<WeaponBundle>>> =
        valorantHttpClient.get(BUNDLES) { langParams() }.awaitResults()

    override suspend fun getWeapons(): DataResult<ValorantApiResponse<List<Weapon>>> =
        valorantHttpClient.get(WEAPONS) { langParams() }.awaitResults()

    override suspend fun getMaps(): DataResult<ValorantApiResponse<List<Maps>>> =
        valorantHttpClient.get(MAPS) { langParams() }.awaitResults()

    override suspend fun getGameUpdates(): DataResult<ValorantApiResponse<List<News>>> =
        httpClient.get(NEWS) { parameter("filter", "game_updates") }.awaitResults()

    override suspend fun getWeaponSkins(): DataResult<ValorantApiResponse<List<WeaponSkin>>> =
        httpClient.get("$WEAPONS/$SKINS") { langParams() }.awaitResults()

    private fun HttpRequestBuilder.langParams(lang: String = LANG_ID) = parameter(LANGUAGE, lang)
}