package dev.ladibells.weather.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class Request(
    val type: String,
    val query: String,
    val language: String,
    val unit: String
)