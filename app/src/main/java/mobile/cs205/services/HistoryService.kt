package mobile.cs205.services

import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.features.logging.LogLevel
import io.ktor.client.features.logging.Logging
import mobile.cs205.data.home.HistoryResponse

/**
 * HistoryService interface defines the functions required for the class implementing it (HistoryServiceImpl)
 * It also configures a companion object responsible to construct and perform dependency injection on the actual class upon creation
 * */
interface HistoryService {
    /**
     * This is an asynchronous function that requests data from the internet
     * In particular, it requests for a list of historical events in Singapore and its date to be displayed in the Home Screen
     * A random page consisting of 10 events are selected
     * */
    suspend fun getHistory(): List<HistoryResponse>

    companion object {
        fun create(): HistoryService {
            return HistoryServiceImpl(
                client = HttpClient(Android) { // This injects the HttpClient for Android to the application
                    // Installs the Logging feature to fully log everything that passes the network in LogCat for ease of debugging
                    install(Logging) {
                        level = LogLevel.ALL
                    }
                    // Installs the KotlinxSerializer which will be a plugin necessary to help us deserialize the data from the internet
                    install(JsonFeature) {
                        serializer = KotlinxSerializer()
                    }
                }
            )
        }
    }
}