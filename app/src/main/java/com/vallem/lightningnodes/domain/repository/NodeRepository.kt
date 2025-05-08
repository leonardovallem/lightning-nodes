package com.vallem.lightningnodes.domain.repository

import com.vallem.lightningnodes.domain.model.Node

interface NodeRepository {
    suspend fun retrieveNodes(): Result<List<Node>>
}