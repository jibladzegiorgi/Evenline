package com.example.evenline

import android.content.Context
import androidx.datastore.core.CorruptionException
import androidx.datastore.core.DataStore
import androidx.datastore.core.Serializer
import androidx.datastore.dataStore
import com.google.protobuf.InvalidProtocolBufferException
import java.io.InputStream
import java.io.OutputStream
import javax.inject.Inject

object SettingsSerializer : Serializer<EvenlineSettings> {
    override val defaultValue: EvenlineSettings = EvenlineSettings.getDefaultInstance()

    override suspend fun readFrom(input: InputStream): EvenlineSettings =
        try {
            // readFrom is already called on the data store background thread
            EvenlineSettings.parseFrom(input)
        } catch (exception: InvalidProtocolBufferException) {
            throw CorruptionException("Cannot read proto.", exception)
        }

    override suspend fun writeTo(t: EvenlineSettings, output: OutputStream) {
        // writeTo is already called on the data store background thread
        t.writeTo(output)
    }
}

val Context.settingsDataStore: DataStore<EvenlineSettings> by dataStore(
    fileName = "settings.pb",
    serializer = SettingsSerializer
)