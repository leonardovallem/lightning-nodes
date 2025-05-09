package com.vallem.lightningnodes.presentation.util.extension

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

private const val DATE_FORMAT = "dd/MM/yy - hh:mm"
private val dateFormatter by lazy { DateTimeFormatter.ofPattern(DATE_FORMAT) }

fun LocalDateTime.toHumanReadableFormat(): String = format(dateFormatter)