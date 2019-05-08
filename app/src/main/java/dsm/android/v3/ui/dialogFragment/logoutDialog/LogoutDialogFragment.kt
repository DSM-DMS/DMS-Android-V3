package dsm.android.v3.ui.dialogFragment.logoutDialog

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
import dsm.android.v3.presentation.di.app.BaseApp
import dsm.android.v3.presentation.viewModel.mypage.logout.LogoutViewModel
import dsm.android.v3.ui.activity.signIn.SignInActivity
import dsm.android.v3.util.DataBindingDialogFragment
import org.jetbrains.anko.support.v4.startActivity
import org.jetbrains.anko.support.v4.toast

class LogoutDialogFragment : DataBindingDialogFragment<DialogLogoutBinding>() {
    override fun inject() = BaseApp.appComponent.injectFragment(this)

    override val layoutId: Int
        get() = R.layout.dialog_logout

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val viewModel = ViewModelProviders.of(this).get(LogoutViewModel::class.java)

        viewModel.toastLiveData.observe(this, Observer { toast(it!!) })
        viewModel.exitLogoutEvent.observe(this, Observer { dialog.dismiss() })
        viewModel.intentToLoginEvent.observe(this, Observer {
            startActivity<SignInActivity>()
            activity!!.finish()
        })
        binding.logoutViewModel = viewModel
        return rootView
    }

    override fun onStart() {
        super.onStart()
        dialog.window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }
}