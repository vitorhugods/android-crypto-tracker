package xyz.schwaab.crypto.base.presentation

import xyz.schwaab.crypto.base.util.UserLocaleProvider
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle

interface DateFormatter {
    fun getShortReadableDate(localDate: LocalDate): String
}

internal class SimpleDateFormatter(private val userLocaleProvider: UserLocaleProvider) :
    DateFormatter {
    override fun getShortReadableDate(localDate: LocalDate): String {
        val formatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT)
            .withLocale(userLocaleProvider.getUserLocale())
        return formatter.format(localDate)
    }
}