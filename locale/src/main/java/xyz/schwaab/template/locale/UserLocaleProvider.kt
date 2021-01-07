package xyz.schwaab.template.locale

import java.util.*

interface UserLocaleProvider {
    fun getLocale(): Locale

    companion object
}
