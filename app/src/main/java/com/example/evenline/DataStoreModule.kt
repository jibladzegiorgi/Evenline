package com.example.evenline

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.DataStoreFactory
import androidx.datastore.dataStoreFile
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataStoreModule {

    @Provides
    @Singleton
    fun providesAppDataStore(
        @ApplicationContext context: Context,
        userPreferencesSerializer: SettingsSerializer,
    ): DataStore<EvenlineSettings> =
        DataStoreFactory.create(
            serializer = userPreferencesSerializer
        ) {
            context.dataStoreFile("settings.pb")
        }
}
