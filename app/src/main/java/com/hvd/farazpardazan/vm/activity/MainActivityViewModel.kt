package com.hvd.farazpardazan.vm.activity

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hvd.farazpardazan.data.net.WeatherRestApi
import com.hvd.farazpardazan.data.net.model.DailyWeather
import com.hvd.farazpardazan.data.net.model.HourlyWeather
import com.hvd.farazpardazan.data.net.model.ResOneCall
import com.hvd.farazpardazan.ui.state.DayState
import com.hvd.farazpardazan.ui.state.UIState
import com.hvd.farazpardazan.util.MyCalendar
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class MainActivityViewModel @ViewModelInject constructor(private val weatherRestApi: WeatherRestApi) :
    ViewModel() {

    //preventing exposure of mutable live data
    private val _weatherData = MutableLiveData<UIState>()
    val weatherData: LiveData<UIState> get() = _weatherData

    private val _dayStateData = MutableLiveData<DayState>()
    val dayStateData: LiveData<DayState> get() = _dayStateData

    private val _hourlyData = MutableLiveData<List<HourlyWeather>>()
    val hourlyData: LiveData<List<HourlyWeather>> get() = _hourlyData

    private val compositeDisposable = CompositeDisposable()

    private var resOneCall: ResOneCall? = null

    init {
        _weatherData.value = UIState.Progress

        val observable = weatherRestApi.currentWeather(35.69, 51.42)
        val disposable = observable
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                this.resOneCall = it
                prepareData(it)
            }, {
                if (it.message != null) {
                    _weatherData.value = UIState.Error(it.message!!)
                } else {
                    _weatherData.value = UIState.Error()
                }
            })
        compositeDisposable.add(disposable)

        _dayStateData.value = MainActivityVMUtils.getDayState()
    }

    private fun prepareData(resOneCall: ResOneCall) {
        _weatherData.value = UIState.Data(resOneCall)
        filterAndEmitHourlyData(resOneCall.daily[0].timeStamp * 1000, resOneCall.hourly)
    }

    private fun filterAndEmitHourlyData(dayTimeStamp: Long, hourlyWeather: List<HourlyWeather>){
        val calendar = MyCalendar(dayTimeStamp)
        val dayStart = calendar.setToDayStart()
        val dayEnd = calendar.setToDayEnd()

        val observable = Observable.fromIterable(hourlyWeather)
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .filter {
                it.timestamp * 1000 in dayStart..dayEnd
            }
            .toList()
        val disposable = observable.subscribe { it ->
            _hourlyData.value = it
        }

        compositeDisposable.add(disposable)
    }

    fun changeSelectedDay(dailyWeather: DailyWeather) {
        filterAndEmitHourlyData(dailyWeather.timeStamp * 1000, resOneCall!!.hourly)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}