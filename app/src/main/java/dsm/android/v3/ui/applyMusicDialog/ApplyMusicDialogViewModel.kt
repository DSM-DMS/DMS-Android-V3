package dsm.android.v3.ui.applyMusicDialog

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.view.View
import dsm.android.v3.ui.applyMusic.ApplyMusicContract

class ApplyMusicDialogViewModel(val contract: ApplyMusicContract): ViewModel() {
    val dialogTitle = MutableLiveData<String>()
    val musicTitleEditText = MutableLiveData<String>()
    val musicArtistEditText = MutableLiveData<String>()

    fun musicClickCancel() {

    }

    fun musicClickSend(view: View) {

    }
}