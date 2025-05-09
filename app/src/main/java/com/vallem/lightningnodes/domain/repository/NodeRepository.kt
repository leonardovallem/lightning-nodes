package com.vallem.lightningnodes.domain.repository

import com.vallem.lightningnodes.domain.model.Node
import kotlinx.collections.immutable.PersistentList

interface NodeRepository {
    suspend fun retrieveNodes(): Result<PersistentList<Node>>
}