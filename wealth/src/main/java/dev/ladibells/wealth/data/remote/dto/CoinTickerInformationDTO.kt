package dev.ladibells.wealth.data.remote.dto

import com.google.gson.annotations.SerializedName
import dev.ladibells.wealth.domain.model.CoinTickerInformation


data class CoinTickerInformationDTO(
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("symbol") val symbol: String,
    @SerializedName("rank") val rank: Int,
    @SerializedName("total_supply") val totalSupply: Long,
    @SerializedName("max_supply") val maxSupply: Long,
    @SerializedName("beta_value") val betaValue: Double,
    @SerializedName("first_data_at") val firstDataAt: String,
    @SerializedName("last_updated") val lastUpdated: String,
    @SerializedName("quotes") val priceInfo: Map<String, CurrencyInfo>
)

fun CoinTickerInformationDTO.toCoinTickerInformation(): CoinTickerInformation {
    return CoinTickerInformation(
        id = id,
        name = name,
        symbol = symbol,
        priceInfo = priceInfo
    )
}
