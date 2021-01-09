package xyz.schwaab.crypto.ticker.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.components.Description
import com.github.mikephil.charting.formatter.ValueFormatter
import com.google.android.material.button.MaterialButton
import com.google.android.material.snackbar.Snackbar
import org.koin.android.viewmodel.ext.android.viewModel
import xyz.schwaab.crypto.base.presentation.BaseBindingFragment
import xyz.schwaab.crypto.ticker.R
import xyz.schwaab.crypto.ticker.databinding.CurrencyTickerFragmentBinding
import java.util.*

class CurrencyTickerFragment(private val dateAxisFormatter: DateAxisFormatter) :
    BaseBindingFragment<CurrencyTickerFragmentBinding>() {

    private val viewModel: CurrencyTickerViewModel by viewModel()

    override val bindingProvider: (
        inflater: LayoutInflater,
        container: ViewGroup?,
        attachToRoot: Boolean
    ) -> CurrencyTickerFragmentBinding
        get() = CurrencyTickerFragmentBinding::inflate

    override fun CurrencyTickerFragmentBinding.onBindingCreated() {
        configureChartData()
        configurePeriodSelection()
        configureErrorMessageDisplay()
    }

    private fun configureErrorMessageDisplay() {
        viewModel.errorEvent.observe(viewLifecycleOwner, { error ->
            Snackbar.make(binding.root, error.resId(), Snackbar.LENGTH_LONG).show()
        })
    }

    private fun CurrencyTickerFragmentBinding.configureChartData() {
        priceHistoryChart.xAxis.valueFormatter = dateAxisFormatter
        priceHistoryChart.description = Description().apply {
            text = root.context.getString(R.string.label_price_of_btc)
        }
        viewModel.displayableChartData.observe(viewLifecycleOwner, { chartData ->
            priceHistoryChart.data = chartData.getLineData()
            priceHistoryChart.notifyDataSetChanged()
            priceHistoryChart.invalidate()
        })
    }

    private fun CurrencyTickerFragmentBinding.configurePeriodSelection() {
        viewModel.selectedChartPeriod.observe(viewLifecycleOwner, { selectedPeriod ->
            @Suppress("WHEN_ENUM_CAN_BE_NULL_IN_JAVA")
            val selectedId = when (selectedPeriod) {
                CurrencyTickerViewModel.ChartPeriod.WEEK -> buttonPeriodWeek.id
                CurrencyTickerViewModel.ChartPeriod.MONTH -> buttonPeriodMonth.id
                CurrencyTickerViewModel.ChartPeriod.YEAR -> buttonPeriodYear.id
            }
            binding.root.findViewById<MaterialButton>(selectedId).isChecked = true
        })

        toggleGroupPeriodSelection.addOnButtonCheckedListener { _, checkedId, isChecked ->
            if (!isChecked) return@addOnButtonCheckedListener
            val newSelection = when (checkedId) {
                buttonPeriodWeek.id -> CurrencyTickerViewModel.ChartPeriod.WEEK
                buttonPeriodMonth.id -> CurrencyTickerViewModel.ChartPeriod.MONTH
                buttonPeriodYear.id -> CurrencyTickerViewModel.ChartPeriod.YEAR
                else -> throw IllegalArgumentException("Unmapped button added to chart period selection")
            }
            viewModel.onChartPeriodSelectionChanged(newSelection)
        }
    }
}