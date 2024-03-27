package mobile.cs205.data.home

import kotlinx.serialization.Serializable

@Serializable
data class HistoryResponse(
    val year: String,
    val month: String,
    val day: String,
    val event: String
)
