package dsm.android.v3.ui.logOutDialog

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dsm.android.v3.R
import dsm.android.v3.databinding.DialogLogoutBinding
import dsm.android.v3.ui.signIn.SignInActivity
import dsm.android.v3.util.DataBindingDialogFragment
import org.jetbrains.anko.support.v4.startActivity
import org.jetbrains.anko.support.v4.toast

class LogoutDialogFragment : DataBindingDialogFragment<DialogLogoutBinding>() {

    override val layoutId: Int
        get() = R.layout.dialog_logout

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding.logoutViewModel = ViewModelProviders.of(this).get(LogoutViewModel::class.java)

        binding.logoutViewModel!!.toastLiveData.observe(this, Observer { toast(it!!) })
        binding.logoutViewModel!!.exitLogoutEvent.observe(this, Observer { dialog.dismiss() })
        binding.logoutViewModel!!.intentToLoginEvent.observe(this, Observer {
            startActivity<SignInActivity>()
            activity!!.finish()
        })
        return rootView
    }

    override fun onStart() {
        super.onStart()
        dialog.window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }
}