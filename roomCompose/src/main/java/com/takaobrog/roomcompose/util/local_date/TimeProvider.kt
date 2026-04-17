package com.takaobrog.roomcompose.util.local_date

interface TimeProvider {
    fun isBeforeNow(targetDate: String?): Boolean
    fun formatterYmd(targetDate: String?): String?
    fun getNow(): String
    fun longToLocalDate(targetDate: Long): String
}