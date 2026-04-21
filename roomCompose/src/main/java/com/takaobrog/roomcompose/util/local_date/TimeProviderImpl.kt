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

    override fun isBeforeNow(targetDate: Long?): Boolean {
        val parserTargetDate = parser(targetDate = targetDate)
        return parserTargetDate?.isBefore(LocalDate.now()) == true
    }

    override fun formatterYmd(targetDate: Long?): String? {
        val formatter = DateTimeFormatter.ofPattern(FORMAT_YYYY_MM_DD)
        val parserTargetDate = parser(targetDate = targetDate)
        return parserTargetDate?.format(formatter)
    }

    override fun getNow(): String =
        OffsetDateTime.now().format(DateTimeFormatter.ISO_OFFSET_DATE_TIME)

    private fun parser(targetDate: Long?): LocalDate? =
        targetDate?.let {
            Instant.ofEpochMilli(it)
                .atZone(ZoneId.systemDefault())
                .toLocalDate()
        }

    companion object {
        private const val FORMAT_YYYY_MM_DD = "yyyy/MM/dd"
    }
}