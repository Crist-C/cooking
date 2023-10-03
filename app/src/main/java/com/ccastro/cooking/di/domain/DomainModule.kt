package com.ccastro.cooking.di.domain

import com.ccastro.cooking.data.repositories.RecetaRepositoryImplement
import com.ccastro.cooking.domain.repositories.IRecetaRepository
import com.ccastro.cooking.domain.useCases.receta.ActualizarFavorito
import com.ccastro.cooking.domain.useCases.receta.ObtenerTodas
import com.ccastro.cooking.domain.useCases.receta.RecetaUseCasesImpement
import com.ccastro.cooking.presentation.useCases.RecetaUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DomainModule {

    @Singleton
    @Provides
    @Named("IRecetaRepository")
    fun provideIRecetaRepository(@Named("RecetaRepositoryImpl") repositoryImplement: RecetaRepositoryImplement): IRecetaRepository = repositoryImplement


    @Singleton
    @Provides
    @Named("RecetasCasosDeUso")
    fun provideRecetaCasosDeUso(@Named("IRecetaRepository") iRecetaRepository: IRecetaRepository) : RecetaUseCases =
        RecetaUseCasesImpement(
            obtenerTodas = ObtenerTodas(iRecetaRepository),
            actualizarFavorito = ActualizarFavorito(iRecetaRepository)
        )
}