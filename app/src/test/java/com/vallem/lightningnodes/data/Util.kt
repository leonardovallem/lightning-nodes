package com.vallem.lightningnodes.data

import com.vallem.lightningnodes.data.model.LocalizedValueDto
import com.vallem.lightningnodes.data.model.NodeDto
import com.vallem.lightningnodes.data.model.SasCount
import java.time.LocalDate

fun sampleNodeDto(
    publicKey: String? = "Public Key",
    alias: String? = "Alias",
    channels: Int? = 200,
    capacity: SasCount? = 2405.0,
    firstSeen: Long? = LocalDate.now().minusDays(100).toEpochDay(),
    updatedAt: Long? = LocalDate.now().toEpochDay(),
    city: LocalizedValueDto? = null,
    country: LocalizedValueDto? = null
) = NodeDto(publicKey, alias, channels, capacity, firstSeen, updatedAt, city, country)