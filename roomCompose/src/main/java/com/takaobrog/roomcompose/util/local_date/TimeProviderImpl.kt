package com.takaobrog.roomcompose.util.local_date

import java.time.Instant
import java.time.LocalDate
import java.time.OffsetDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TimeProviderImpl @Inject constructor() : TimeProvider {

    override fun isBeforeNow(targetDate: String?): Boolean {
        val parserTargetDate = parser(targetDate = targetDate)
        return parserTargetDate?.isBefore(LocalDate.now()) == true
    }

    override fun formatterYmd(targetDate: String?): String? {
        val formatter = DateTimeFormatter.ofPattern(FORMAT_YYYY_MM_DD)
        val parserTargetDate = parser(targetDate = targetDate)
        return parserTargetDate?.format(formatter)
    }

    override fun getNow(): String =
        OffsetDateTime.now().format(DateTimeFormatter.ISO_OFFSET_DATE_TIME)

    override fun longToLocalDate(targetDate: Long): String =
        Instant.ofEpochMilli(targetDate)
            .atZone(ZoneId.systemDefault())
            .toLocalDate().toString()

    private fun parser(targetDate: String?): LocalDate? =
        targetDate?.let { if (it.isNotEmpty()) LocalDate.parse(it) else null }

    companion object {
        private const val FORMAT_YYYY_MM_DD = "yyyy/MM/dd"
    }
}