package com.vallem.lightningnodes.data.mapper

import assertk.assertThat
import assertk.assertions.isNotNull
import assertk.assertions.isNull
import assertk.assertions.prop
import com.vallem.lightningnodes.data.model.LocalizedValueDto
import com.vallem.lightningnodes.data.sampleNodeDto
import com.vallem.lightningnodes.domain.model.Node
import org.junit.Test

class NodeMapperTest {
    @Test
    fun `given publicKey is null, when toDomain is called, then should return null`() {
        val dto = sampleNodeDto(publicKey = null)
        val result = dto.toDomain()
        assertThat(result).isNull()
    }

    @Test
    fun `given alias is null, when toDomain is called, then should return null`() {
        val dto = sampleNodeDto(alias = null)
        val result = dto.toDomain()
        assertThat(result).isNull()
    }

    @Test
    fun `given channels is null, when toDomain is called, then should return null`() {
        val dto = sampleNodeDto(channels = null)
        val result = dto.toDomain()
        assertThat(result).isNull()
    }

    @Test
    fun `given capacity is null, when toDomain is called, then should return null`() {
        val dto = sampleNodeDto(capacity = null)
        val result = dto.toDomain()
        assertThat(result).isNull()
    }

    @Test
    fun `given firstSeen is null, when toDomain is called, then should return null`() {
        val dto = sampleNodeDto(firstSeen = null)
        val result = dto.toDomain()
        assertThat(result).isNull()
    }

    @Test
    fun `given updatedAt is null, when toDomain is called, then should return null`() {
        val dto = sampleNodeDto(updatedAt = null)
        val result = dto.toDomain()
        assertThat(result).isNull()
    }

    @Test
    fun `given publicKey is blank, when toDomain is called, then should return null`() {
        val dto = sampleNodeDto(publicKey = " ")
        val result = dto.toDomain()
        assertThat(result).isNull()
    }

    @Test
    fun `given alias is blank, when toDomain is called, then should return null`() {
        val dto = sampleNodeDto(alias = " ")
        val result = dto.toDomain()
        assertThat(result).isNull()
    }

    @Test
    fun `given mandatory fields are valid, when toDomain is called, then should return Node`() {
        val dto = sampleNodeDto()
        val result = dto.toDomain()
        assertThat(result).isNotNull()
    }

    @Test
    fun `given location fields are valid, when toDomain is called, then created Node should have valid location`() {
        val dto = sampleNodeDto(
            city = LocalizedValueDto(ptBr = "São Paulo"),
            country = LocalizedValueDto(en = "Brazil"),
        )

        val result = dto.toDomain()

        assertThat(result).isNotNull().prop(Node::location).isNotNull()
    }
}
