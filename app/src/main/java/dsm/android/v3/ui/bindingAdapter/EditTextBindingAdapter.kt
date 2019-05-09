package dsm.android.v3.ui.bindingAdapter

import android.databinding.BindingAdapter
import android.view.View
import android.widget.EditText

class EditTextBindingAdapter {
    companion object {
        @BindingAdapter("onFocus")
        fun onFocusChange(editText: EditText, listener : View.OnFocusChangeListener) {
            editText!!.onFocusChangeListener = listener

        }
    }
}
