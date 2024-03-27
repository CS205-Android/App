package mobile.cs205.data.remote

import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.headers
import mobile.cs205.data.remote.data.HistoryResponse

class HistoryServiceImpl(private val client: HttpClient) : HistoryService {
    override suspend fun getHistory(): List<HistoryResponse> {
        return try {
            client.get("https://api.api-ninjas.com/v1/historicalevents?text=singapore&offset=${(0..33).random()}") {
                headers {
                    append("X-Api-Key", "ROaU12cr++BwayL1ufAHQQ==p5xRuBgvx7CQlPOZ")
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
            listOf(
                HistoryResponse(
                    "1971",
                    "01",
                    "22",
                    "The Singapore Declaration, one of the two most important documents to the uncodified constitution of the Commonwealth of Nations, is issued."
                )
            )
        }


    }
}
