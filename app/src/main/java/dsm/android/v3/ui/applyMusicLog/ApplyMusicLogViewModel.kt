package dsm.android.v3.ui.applyMusicLog

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.view.View

class ApplyMusicLogViewModel(val contract: ApplyMusicLogContract): ViewModel() {

    val logTitle = MutableLiveData<String>()
    val logExplanation = MutableLiveData<String>()

    val firstMusicName = MutableLiveData<String>()
    val firstMusicArtist = MutableLiveData<String>()
    val firstMusicStudent = MutableLiveData<String>()
    val secondMusicName = MutableLiveData<String>()
    val secondMusicArtist = MutableLiveData<String>()
    val secondMusicStudent = MutableLiveData<String>()

    fun applyMusicLogClickBack(view: View) {

    }

    fun firstCardClick(view: View){

    }

    fun secondCardClick(view: View) {

    }
}