package xyz.schwaab.crypto.ticker.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.subscribeBy
import xyz.schwaab.crypto.base.presentation.BaseViewModel
import xyz.schwaab.crypto.base.util.LiveEvent
import xyz.schwaab.crypto.ticker.data.ChartData
import xyz.schwaab.crypto.ticker.data.ChartType
import xyz.schwaab.crypto.ticker.domain.RequestChartData

class CurrencyTickerViewModel(
    private val requestChartData: RequestChartData,
    private val mapToDisplayableData: (ChartData) -> DisplayableChartData
) : BaseViewModel() {

    private val _selectedChartPeriod = MutableLiveData(ChartPeriod.WEEK)
    val selectedChartPeriod: LiveData<ChartPeriod> = _selectedChartPeriod

    val errorEvent = LiveEvent<TickerUserJourneyErrors>()

    private val _displayableChartData = MutableLiveData<DisplayableChartData>()
    val displayableChartData: LiveData<DisplayableChartData> = _displayableChartData

    private val compositeDisposable = CompositeDisposable()

    fun onChartPeriodSelectionChanged(chartPeriod: ChartPeriod) {
        _selectedChartPeriod.postValue(chartPeriod)
        updateChartData(chartPeriod)
    }

    private fun updateChartData(chartPeriod: ChartPeriod) {
        val disposable = requestChartData(
            RequestChartData.Parameters(
                ChartType.MARKET_PRICE,
                chartPeriod.amountOfDays
            )
        ).subscribeBy { result ->
            when (result) {
                is RequestChartData.Result.Success -> _displayableChartData.postValue(
                    mapToDisplayableData(result.chartData)
                )
                RequestChartData.Result.Failure.LackOfConnection -> errorEvent.postValue(
                    TickerUserJourneyErrors.LACK_OF_CONNECTION
                )
                RequestChartData.Result.Failure.ServiceUnavailable -> errorEvent.postValue(
                    TickerUserJourneyErrors.SERVICE_UNAVAILABLE
                )
                is RequestChartData.Result.Failure.UnknownError -> errorEvent.postValue(
                    TickerUserJourneyErrors.UNKNOWN
                )
            }
        }
        compositeDisposable.add(disposable)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

    enum class ChartPeriod(val amountOfDays: Int) {
        WEEK(7), MONTH(31), YEAR(365)
    }
}