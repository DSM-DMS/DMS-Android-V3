@file:JvmName("SignInBindingAdapter")

package dsm.android.v3.ui.signIn

import android.arch.lifecycle.MutableLiveData
import android.databinding.BindingAdapter
import android.databinding.InverseBindingAdapter
import android.databinding.InverseBindingListener
import android.widget.EditText
import org.jetbrains.anko.sdk27.coroutines.onFocusChange

@BindingAdapter("onFocus")
fun EditText.setOnFocus(isFocus: MutableLiveData<Boolean>) {
}

@InverseBindingAdapter(attribute = "onFocus")
fun EditText.getOnFocus() = isFocused

@BindingAdapter("onFocusAttrChanged")
fun EditText.setOnFocusChangedListener(inverseListener: InverseBindingListener) {
    onFocusChange { _, _ -> inverseListener.onChange() }
}