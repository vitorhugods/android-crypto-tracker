package xyz.schwaab.crypto.ticker.presentation

import androidx.annotation.StringRes
import xyz.schwaab.crypto.ticker.R

enum class TickerUserJourneyErrors {
    LACK_OF_CONNECTION,
    SERVICE_UNAVAILABLE,
    UNKNOWN
}

@StringRes
fun TickerUserJourneyErrors.resId(): Int {
    return when (this) {
        TickerUserJourneyErrors.LACK_OF_CONNECTION -> R.string.error_connection_problems
        TickerUserJourneyErrors.SERVICE_UNAVAILABLE -> R.string.error_service_unavailable
        TickerUserJourneyErrors.UNKNOWN -> R.string.error_service_unavailable
    }
}