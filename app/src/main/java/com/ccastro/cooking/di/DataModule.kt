package com.ccastro.cooking.di

import com.ccastro.cooking.data.api.recetas.RecetaDAO
import com.ccastro.cooking.data.repositories.IRecetaRepositoryImplement
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
object DataModule {

    // DEPENDENCIAS DE RETROFIT
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
    @Named("RecetaDAO")
    fun provideRecetaDAO(@Named("APIClientRecetas") apiClient: Retrofit): RecetaDAO =
        apiClient.create(RecetaDAO::class.java)

    @Provides
    @Named("RecetaRepositoryImpl")
    fun providesRecetaRepositoryImpl(@Named("RecetaDAO") recetaDAO: RecetaDAO):
            IRecetaRepositoryImplement = IRecetaRepositoryImplement(recetaDAO)

}