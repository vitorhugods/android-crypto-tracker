package xyz.schwaab.crypto.ticker.presentation

import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.formatter.ValueFormatter
import xyz.schwaab.crypto.base.presentation.DateFormatter
import java.time.Instant
import java.time.ZoneId
import kotlin.math.roundToLong

class DateAxisFormatter(private val dateFormatter: DateFormatter) : ValueFormatter() {
    override fun getAxisLabel(value: Float, axis: AxisBase?): String {
        val localDate = Instant.ofEpochSecond(value.roundToLong())
            .atZone(ZoneId.systemDefault())
            .toLocalDate()
        return dateFormatter.getShortReadableDate(localDate)
    }
}