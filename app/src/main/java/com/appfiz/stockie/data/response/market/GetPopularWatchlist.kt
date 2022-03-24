package com.appfiz.stockie.data.response.market
import kotlinx.serialization.*

@Serializable
data class GetPopularWatchlist(
    val finance: Finance
)

@Serializable
data class Finance(
    val result: List<Section>,
)

@Serializable
data class Section(
    val id: String,
    val name: String,
    val total: Int,
    val portfolios: List<Portfolio>
)

@Serializable
data class Portfolio(
    val userId: String,
    val pfId: String,
    val name: String,
    val shortDescription: String,
    val followerCount: Long,
    val dailyPercentGain: Double,
    val createdAt: Long,
    val updatedAt: Long,
    val backgroundImage: BackgroundImage? = null
)

@Serializable
data class BackgroundImage(
    @SerialName("ios:size=medium")
    val sizeMedium: Image,
    @SerialName("ios:size=card_large_fixed")
    val sizeLarge: Image
)

@Serializable
data class Image(
    val width: Int,
    val height: Int,
    val url: String
)
