package id.my.mufidz.valwik.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import id.my.mufidz.valwik.data.network.ApiServices
import id.my.mufidz.valwik.usecase.*
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Singleton
    @Provides
    fun provideGetAgentsUseCase(apiServices: ApiServices): GetAgentsUseCase =
        GetAgentsUseCase(apiServices)

    @Singleton
    @Provides
    fun provideGetMapsUseCase(apiServices: ApiServices): GetMapsUseCase =
        GetMapsUseCase(apiServices)

    @Singleton
    @Provides
    fun provideGetWeaponsUseCase(apiServices: ApiServices): GetWeaponUseCase =
        GetWeaponUseCase(apiServices)

    @Singleton
    @Provides
    fun provideGetWeaponBundlesUseCase(apiServices: ApiServices): GetWeaponBundleUseCase =
        GetWeaponBundleUseCase(apiServices)

    @Singleton
    @Provides
    fun provideGetGameUpdatesUseCase(apiServices: ApiServices): GetGameUpdateUseCase =
        GetGameUpdateUseCase(apiServices)

}