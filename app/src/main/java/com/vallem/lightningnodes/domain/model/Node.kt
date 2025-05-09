package com.vallem.lightningnodes.domain.model

import java.math.BigDecimal
import java.time.LocalDateTime

typealias BitCoinCount = BigDecimal

data class Node(
    val publicKey: String,
    val alias: String,
    val channels: Int,
    val capacity: BitCoinCount,
    val firstSeen: LocalDateTime,
    val updatedAt: LocalDateTime,
    val location: NodeLocation,
)

data class NodeLocation(val city: String?, val country: String?) {
    val isUnknown get() = city == null && country == null
}