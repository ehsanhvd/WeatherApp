package com.hvd.farazpardazan.vm.activity

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hvd.farazpardazan.data.net.WeatherRestApi
import com.hvd.farazpardazan.ui.state.UIState
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class MainActivityViewModel @ViewModelInject constructor(private val weatherRestApi: WeatherRestApi) : ViewModel() {

    private val _navData = MutableLiveData<UIState>()
    val navData: LiveData<UIState>
        get() = _navData

    private val compositeDisposable = CompositeDisposable()

    init {
        _navData.value = UIState.Progress

        val observable = weatherRestApi.currentWeather(35.69, 51.42)
        val disposable = observable.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _navData.value = UIState.Data(it)
            }, {
                if (it.message != null) {
                    _navData.value = UIState.Error(it.message!!)
                } else {
                    _navData.value = UIState.Error()
                }
            })

        compositeDisposable.add(disposable)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}