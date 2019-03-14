package dsm.android.v3.ui.applyMusic

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.ViewModel
import dsm.android.v3.util.LifecycleCallback

class ApplyMusicViewModel(val contract: ApplyMusicContract): ViewModel(), LifecycleCallback {

    override fun apply(event: Lifecycle.Event) {
        when(event){
            Lifecycle.Event.ON_START -> {
            }
        }
    }


    fun applyMusicClickBack() = contract.closeApplyMusic()

    fun applyMusicClickLog() {

    }

}