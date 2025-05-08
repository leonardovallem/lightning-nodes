package com.vallem.lightningnodes.domain.model

import java.math.BigDecimal
import java.time.LocalDate

typealias BitCoinCount = BigDecimal

data class Node(
    val publicKey: String,
    val alias: String,
    val channels: Int,
    val capacity: BitCoinCount,
    val firstSeen: LocalDate,
    val updatedAt: LocalDate,
    val location: NodeLocation,
)

data class NodeLocation(val city: String?, val country: String?)