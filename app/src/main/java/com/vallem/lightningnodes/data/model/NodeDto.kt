package com.vallem.lightningnodes.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

typealias SasCount = Double

@Serializable
data class NodeDto(
    @SerialName("publicKey") val publicKey: String? = null,
    @SerialName("alias") val alias: String? = null,
    @SerialName("channels") val channels: Int? = null,
    @SerialName("capacity") val capacity: SasCount? = null,
    @SerialName("firstSeen") val firstSeen: Long? = null,
    @SerialName("updatedAt") val updatedAt: Long? = null,
    @SerialName("city") val city: LocalizedValueDto? = null,
    @SerialName("country") val country: LocalizedValueDto? = null,
)

@Serializable
data class LocalizedValueDto(
    @SerialName("de") val de: String? = null,
    @SerialName("en") val en: String? = null,
    @SerialName("es") val es: String? = null,
    @SerialName("fr") val fr: String? = null,
    @SerialName("ja") val ja: String? = null,
    @SerialName("pt-BR") val ptBr: String? = null,
    @SerialName("ru") val ru: String? = null,
    @SerialName("zh-CN") val zhCn: String? = null,
)