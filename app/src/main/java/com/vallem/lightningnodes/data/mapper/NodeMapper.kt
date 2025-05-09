package com.vallem.lightningnodes.data.mapper

import com.vallem.lightningnodes.data.model.NodeDto
import com.vallem.lightningnodes.data.model.SasCount
import com.vallem.lightningnodes.domain.model.Language
import com.vallem.lightningnodes.domain.model.Node
import com.vallem.lightningnodes.domain.model.NodeLocation
import java.math.BigDecimal
import java.time.Instant
import java.time.LocalDateTime
import java.util.TimeZone

fun NodeDto.toDomain() = run {
    val mandatoryFields = listOf(
        publicKey?.takeIf(String::isNotBlank),
        alias?.takeIf(String::isNotBlank),
        channels,
        capacity,
        firstSeen,
        updatedAt,
    )

    if (mandatoryFields.any { it == null }) return@run null

    val translatedCity = city?.get(Language.Portuguese, Language.English)
    val translatedCountry = country?.get(Language.Portuguese, Language.English)

    Node(
        publicKey = publicKey!!,
        alias = alias!!,
        channels = channels!!,
        capacity = capacity!!.toBtc(),
        firstSeen = firstSeen!!.toLocalDateTime(),
        updatedAt = updatedAt!!.toLocalDateTime(),
        location = NodeLocation(city = translatedCity, country = translatedCountry)
    )
}

private fun Long.toLocalDateTime(): LocalDateTime {
    val instant = Instant.ofEpochSecond(this)
    return LocalDateTime.ofInstant(instant, TimeZone.getDefault().toZoneId())
}

private val sasBtcRatio = BigDecimal("100000000")

private fun SasCount.toBtc() = toBigDecimal().divide(sasBtcRatio)
