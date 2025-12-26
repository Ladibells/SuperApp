package dev.ladibells.wealth.domain.model

import com.google.gson.annotations.SerializedName
import dev.ladibells.wealth.data.remote.dto.CurrencyInfo

data class CoinTickerInformation(
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("symbol") val symbol: String,
    @SerializedName("quotes") val priceInfo: Map<String, CurrencyInfo>

)
