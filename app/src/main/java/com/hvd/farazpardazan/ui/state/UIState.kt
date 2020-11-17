package com.hvd.farazpardazan.ui.state

sealed class UIState {
    object Progress : UIState()
    class Data<State>(val state: State) : UIState()
    class Error(val msg: String = "Unknown error occurred") : UIState()
}