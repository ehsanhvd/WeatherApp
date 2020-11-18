package com.hvd.farazpardazan.vm.activity

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hvd.farazpardazan.data.net.WeatherRestApi
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

    private val _weatherData = MutableLiveData<UIState>()
    val weatherData: LiveData<UIState> get() = _weatherData

    private val _dayStateData = MutableLiveData<DayState>()
    val dayStateData: LiveData<DayState> get() = _dayStateData

    private val compositeDisposable = CompositeDisposable()

    init {
        _weatherData.value = UIState.Progress

        val observable = weatherRestApi.currentWeather(35.69, 51.42)
        val disposable = observable
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
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

    // prepare and filter data for ui
    private fun prepareData(resOneCall: ResOneCall) {
        val calendar = MyCalendar()
        val dayStart = calendar.setToDayStart()
        val dayEnd = calendar.setToDayEnd()

        val observable = Observable.fromIterable(resOneCall.hourly)
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .filter {
                it.timestamp in dayStart..dayEnd
            }
            .toList()
        val disposable = observable.subscribe { it ->
            resOneCall.hourly = it
            _weatherData.value = UIState.Data(resOneCall)
        }

        compositeDisposable.add(disposable)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}