package com.ccastro.cooking.di.data

import android.content.Context
import androidx.room.Room
import com.ccastro.cooking.data.dataSources.local.LocalDataBase
import com.ccastro.cooking.data.dataSources.local.daos.RecetaDAO
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataDBModule {

    @Provides
    @Singleton
    @Named("LocalDataBase")
    fun provideLocalDB(@ApplicationContext context: Context): LocalDataBase {
        return Room.databaseBuilder(context, LocalDataBase::class.java, "cooking_db")
            .fallbackToDestructiveMigration() // Destruye la última versión en caso de crear una nueva
            .build()
    }

    @Provides
    @Singleton
    @Named("RecetaDbDAO")
    fun providesRecetaDAO(@Named("LocalDataBase") localDB: LocalDataBase): RecetaDAO = localDB.RecetasDAO()

}