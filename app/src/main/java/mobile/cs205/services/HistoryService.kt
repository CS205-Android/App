package mobile.cs205.services

import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.features.logging.LogLevel
import io.ktor.client.features.logging.Logging
import mobile.cs205.data.home.HistoryResponse

interface HistoryService {
    suspend fun getHistory(): List<HistoryResponse>

    companion object {
        fun create(): HistoryService {
            return HistoryServiceImpl(
                client = HttpClient(Android){
                    install(Logging){
                        level = LogLevel.ALL
                    }
                    install(JsonFeature){
                        serializer = KotlinxSerializer()
                    }
                }
            )
        }
    }
}