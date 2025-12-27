package dev.ladibells.superapp.data

@kotlinx.serialization.Serializable
data class FestivalsData(
    val id: Int,
    val festivalName: String? = null,
    val festivalDate: String? = null,
    val festivalDescription: String? = null,
)
