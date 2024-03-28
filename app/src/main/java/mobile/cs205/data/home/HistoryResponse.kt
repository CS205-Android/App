package mobile.cs205.data.home

import kotlinx.serialization.Serializable

/**
 * This data class mainly serves as the serializer when we receive the response from APINinja to display a random historical event in Singapore
 * It is also used as placeholder for the initial data when the data has yet to complete its fetch
 * @param year : Indicates the year when the event happened
 * @param month : Indicates the month when the event happened
 * @param day : Indicates the day when the event happened
 * @param event : Contains description about the historical event
 * */
@Serializable
data class HistoryResponse(
    val year: String,
    val month: String,
    val day: String,
    val event: String
)
