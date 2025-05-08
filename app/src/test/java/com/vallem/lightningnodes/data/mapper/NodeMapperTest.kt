package com.vallem.lightningnodes.data.mapper

import assertk.assertThat
import assertk.assertions.isNotNull
import assertk.assertions.isNull
import assertk.assertions.prop
import com.vallem.lightningnodes.data.model.LocalizedValueDto
import com.vallem.lightningnodes.data.model.NodeDto
import com.vallem.lightningnodes.data.model.SasCount
import com.vallem.lightningnodes.domain.model.Node
import org.junit.Test
import java.time.LocalDate

class NodeMapperTest {
    @Test
    fun `given publicKey is null, when toDomain is called, then should return null`() {
        val dto = sampleDto(publicKey = null)
        val result = dto.toDomain()
        assertThat(result).isNull()
    }

    @Test
    fun `given alias is null, when toDomain is called, then should return null`() {
        val dto = sampleDto(alias = null)
        val result = dto.toDomain()
        assertThat(result).isNull()
    }

    @Test
    fun `given channels is null, when toDomain is called, then should return null`() {
        val dto = sampleDto(channels = null)
        val result = dto.toDomain()
        assertThat(result).isNull()
    }

    @Test
    fun `given capacity is null, when toDomain is called, then should return null`() {
        val dto = sampleDto(capacity = null)
        val result = dto.toDomain()
        assertThat(result).isNull()
    }

    @Test
    fun `given firstSeen is null, when toDomain is called, then should return null`() {
        val dto = sampleDto(firstSeen = null)
        val result = dto.toDomain()
        assertThat(result).isNull()
    }

    @Test
    fun `given updatedAt is null, when toDomain is called, then should return null`() {
        val dto = sampleDto(updatedAt = null)
        val result = dto.toDomain()
        assertThat(result).isNull()
    }

    @Test
    fun `given publicKey is blank, when toDomain is called, then should return null`() {
        val dto = sampleDto(publicKey = " ")
        val result = dto.toDomain()
        assertThat(result).isNull()
    }

    @Test
    fun `given alias is blank, when toDomain is called, then should return null`() {
        val dto = sampleDto(alias = " ")
        val result = dto.toDomain()
        assertThat(result).isNull()
    }

    @Test
    fun `given mandatory fields are valid, when toDomain is called, then should return Node`() {
        val dto = sampleDto()
        val result = dto.toDomain()
        assertThat(result).isNotNull()
    }

    @Test
    fun `given location fields are valid, when toDomain is called, then created Node should have valid location`() {
        val dto = sampleDto(
            city = LocalizedValueDto(ptBr = "São Paulo"),
            country = LocalizedValueDto(en = "Brazil"),
        )

        val result = dto.toDomain()

        assertThat(result).isNotNull().prop(Node::location).isNotNull()
    }

    private fun sampleDto(
        publicKey: String? = "Public Key",
        alias: String? = "Alias",
        channels: Int? = 200,
        capacity: SasCount? = 2405.0,
        firstSeen: Long? = LocalDate.now().minusDays(100).toEpochDay(),
        updatedAt: Long? = LocalDate.now().toEpochDay(),
        city: LocalizedValueDto? = null,
        country: LocalizedValueDto? = null
    ) = NodeDto(publicKey, alias, channels, capacity, firstSeen, updatedAt, city, country)
}
