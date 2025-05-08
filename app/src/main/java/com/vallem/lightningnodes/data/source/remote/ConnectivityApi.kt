package com.vallem.lightningnodes.data.source.remote

import com.vallem.lightningnodes.data.model.NodeDto
import retrofit2.http.GET

interface ConnectivityApi {
    @GET("/v1/lightning/nodes/rankings/connectivity")
    suspend fun retrieveRanking(): List<NodeDto>
}