package com.vallem.lightningnodes.data.mapper

import com.vallem.lightningnodes.data.model.LocalizedValueDto
import com.vallem.lightningnodes.domain.model.Language

fun LocalizedValueDto.get(vararg priorityLanguages: Language): String? {
    for (language in priorityLanguages) {
        val value = when (language) {
            Language.German -> de
            Language.English -> en
            Language.Spanish -> es
            Language.French -> fr
            Language.Japanese -> ja
            Language.Portuguese -> ptBr
            Language.Russian -> ru
            Language.Mandarin -> zhCn
        }?.ifBlank { null }

        if (value != null) return value
    }

    return null
}