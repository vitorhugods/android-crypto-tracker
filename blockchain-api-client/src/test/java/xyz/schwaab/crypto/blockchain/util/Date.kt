package xyz.schwaab.crypto.blockchain.util

import java.util.*

/**
 * Creates a [Date] using human readable integers for [year], [month] and [day]
 */
fun createDate(year: Int, month: Int, day: Int): Date {
    val calendar = Calendar.getInstance()
    calendar.set(Calendar.YEAR, year)
    calendar.set(Calendar.MONTH, month - 1)
    calendar.set(Calendar.DAY_OF_MONTH, day)
    return Date.from(calendar.toInstant())
}