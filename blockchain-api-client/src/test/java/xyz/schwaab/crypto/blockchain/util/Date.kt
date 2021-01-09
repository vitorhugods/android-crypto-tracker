package xyz.schwaab.crypto.blockchain.util

import java.time.LocalDate


/**
 * Creates a [LocalDate] using human readable integers for [year], [month] and [day]
 */
fun createDate(year: Int, month: Int, day: Int): LocalDate {
    return LocalDate.of(year, month, day)
}