@file:JvmName("ApplyGoingBindingAdapter")

package dsm.android.v3.ui.applyGoingDoc

import android.arch.lifecycle.MutableLiveData
import android.databinding.BindingAdapter
import android.support.design.widget.TextInputLayout

@BindingAdapter("errorValue")
fun TextInputLayout.setErrorValue(data: MutableLiveData<String>) {
    error = data.value
}