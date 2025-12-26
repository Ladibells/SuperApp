package dev.ladibells.wealth.domain.model

import com.google.gson.annotations.SerializedName
import dev.ladibells.wealth.data.remote.dto.Links
import dev.ladibells.wealth.data.remote.dto.Tag
import dev.ladibells.wealth.data.remote.dto.TeamMember

data class CoinDetail(
    val coinId: String,
    val name: String,
    val description: String,
    val symbol: String,
    val rank: Int,
    val isActive: Boolean,
    val tags: List<Tag>,
    val team: List<TeamMember>,
    val links: Links,
    val logo: String,
    val author: String?,
    val orgStructure: String,
    val hashAlgorithm: String
)
