package com.takaobrog.roomcompose.util.local_date

interface TimeProvider {
    fun isBeforeNow(targetDate: Long?): Boolean
    fun formatterYmd(targetDate: Long?): String?
    fun getNow(): String
}