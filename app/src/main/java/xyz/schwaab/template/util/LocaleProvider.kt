package xyz.schwaab.template.util

import android.content.Context
import android.content.res.Configuration
import android.content.res.Resources
import xyz.schwaab.template.locale.UserLocaleProvider
import java.util.*

class DefaultUserLocaleProvider : UserLocaleProvider {
    override fun getLocale(): Locale {
        return Locale.getDefault()
    }
}

fun UserLocaleProvider.Companion.default(): UserLocaleProvider = DefaultUserLocaleProvider()

fun Context.getLocalizedResources(userLocaleProvider: UserLocaleProvider): Resources {
    var conf: Configuration = resources.configuration
    conf = Configuration(conf)
    conf.setLocale(userLocaleProvider.getLocale())
    val localizedContext: Context = createConfigurationContext(conf)
    return localizedContext.resources
}