package com.ccastro.cooking.di.data

import com.ccastro.cooking.data.api.recetas.RecetaApiDAO
import com.ccastro.cooking.data.dataSources.local.daos.RecetaDAO
import com.ccastro.cooking.data.repositories.RecetaRepositoryImplement
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataApisModule {

    // DEPENDENCIAS PARA EL CONSUMO DE API's
    @Singleton
    @Provides
    @Named("UrlBaseRecetas")
    fun provideUrlBaseEndPointRecetas() = "https://demo0673634.mockable.io"

    @Provides
    @Named("APIClientRecetas")
    fun provideApiClientConection(@Named("UrlBaseRecetas") urlBase: String): Retrofit {
        return Retrofit.Builder()
            .baseUrl(urlBase)
            .client(
                OkHttpClient()
                    .newBuilder()
                    .build()
            )
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Named("RecetaApiDAO")
    fun provideRecetaDAO(@Named("APIClientRecetas") apiClient: Retrofit): RecetaApiDAO =
        apiClient.create(RecetaApiDAO::class.java)

    @Provides
    @Named("RecetaRepositoryImpl")
    fun providesRecetaRepositoryImpl(
        @Named("RecetaApiDAO") recetaApiDAO: RecetaApiDAO,
        @Named("RecetaDbDAO") recetaDbDAO: RecetaDAO):
            RecetaRepositoryImplement = RecetaRepositoryImplement(recetaApiDAO, recetaDbDAO)

}