package com.vallem.lightningnodes.data.repository

import com.vallem.lightningnodes.data.mapper.toDomain
import com.vallem.lightningnodes.data.mapper.toFriendlyException
import com.vallem.lightningnodes.data.model.NodeDto
import com.vallem.lightningnodes.data.source.remote.ConnectivityApi
import com.vallem.lightningnodes.domain.repository.NodeRepository
import kotlinx.collections.immutable.toPersistentList
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException

class NodeRepositoryImpl(
    private val connectivityApi: ConnectivityApi,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO,
) : NodeRepository {
    override suspend fun retrieveNodes() = withContext(ioDispatcher) {
        try {
            val nodes = connectivityApi.retrieveRanking()
                .mapNotNull(NodeDto::toDomain)
                .toPersistentList()

            Result.success(nodes)
        } catch (exception: HttpException) {
            Result.failure(exception.toFriendlyException())
        }
    }
}