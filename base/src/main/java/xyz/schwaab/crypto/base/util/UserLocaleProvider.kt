package xyz.schwaab.crypto.base.util

import java.util.*

interface UserLocaleProvider {
    fun getUserLocale(): Locale
}

internal class SystemLocaleProvider: UserLocaleProvider{
    override fun getUserLocale(): Locale {
        return Locale.getDefault()
    }
}