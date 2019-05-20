package dsm.android.v3.presentation.viewModel.putIn

import android.arch.lifecycle.ViewModel
import dsm.android.v3.util.SingleLiveEvent

class PutInViewModel : ViewModel() {
    val extensionLiveEvent = SingleLiveEvent<Any>()
    val stayLiveEvent = SingleLiveEvent<Any>()
    val musicLiveEvent = SingleLiveEvent<Any>()
    val goingOutLiveEvent = SingleLiveEvent<Any>()

    fun extensionEventCall() {
        extensionLiveEvent.call()
    }

    fun stayEventCall() {
        stayLiveEvent.call()
    }

    fun musicEventCall() {
        musicLiveEvent.call()
    }

    fun goingOutEventCall() {
        goingOutLiveEvent.call()
    }
}
