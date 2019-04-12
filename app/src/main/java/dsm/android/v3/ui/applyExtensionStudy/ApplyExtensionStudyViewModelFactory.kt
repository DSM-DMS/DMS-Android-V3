package dsm.android.v3.ui.applyExtensionStudy

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.view.View

class ApplyExtensionStudyViewModelFactory(val classView: View, val timeView: View): ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T
            = modelClass.getConstructor(View::class.java, View::class.java).newInstance(classView, timeView)
}