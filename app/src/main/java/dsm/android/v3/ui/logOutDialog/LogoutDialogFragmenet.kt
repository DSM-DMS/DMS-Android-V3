package dsm.android.v3.ui.logOutDialog

import android.arch.lifecycle.ViewModelProviders
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dsm.android.v3.R
import dsm.android.v3.databinding.DialogLogoutBinding
import dsm.android.v3.util.DataBindingDialogFragment
import org.jetbrains.anko.support.v4.toast

class LogoutDialogFragmenet: DataBindingDialogFragment<DialogLogoutBinding>(), LogOutContract {
    override val layoutId: Int
        get() = R.layout.dialog_logout

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val factory = LogOutViewModelFactory(this)
        binding.logOutViewModel = ViewModelProviders.of(this, factory).get(LogOutViewModel::class.java)
        return rootView
    }

    override fun onStart() {
        super.onStart()
        dialog.window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }

    override fun createShortToast(text: String) = toast(text).show()

    override fun exitLogout() = dialog.dismiss()
}