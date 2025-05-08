package com.vallem.lightningnodes.data.mapper

import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isNull
import com.vallem.lightningnodes.data.model.LocalizedValueDto
import com.vallem.lightningnodes.domain.model.Language
import org.junit.Test

class LocalizedValueTest {
    @Test
    fun `given no specified languages, when LocalizedValueDto$get is called, then should return null`() {
        val result = fullDto.get()
        assertThat(result).isNull()
    }

    @Test
    fun `given value not found for specified language, when LocalizedValueDto$get is called, then should return null`() {
        val result = incompleteDto.get(Language.French, Language.English)
        assertThat(result).isNull()
    }

    @Test
    fun `given value found for one of specified languages, when LocalizedValueDto$get is called, then should return the value`() {
        val result = incompleteDto.get(Language.French, Language.English, Language.German)
        assertThat(result).isEqualTo(incompleteDto.de)
    }

    private companion object {
        val fullDto = LocalizedValueDto("de", "en", "es", "fr", "ja", "pt-BR", "ru", "zh-CN")
        val incompleteDto = LocalizedValueDto("de", null, null, null, null, null, null, null)
    }
}