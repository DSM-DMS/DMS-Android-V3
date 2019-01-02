package dsm.android.v3.ui.mypage

import android.arch.lifecycle.ViewModelProviders
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dsm.android.v3.R
import dsm.android.v3.databinding.DialogBugReportBinding
import dsm.android.v3.util.DataBindingDialogFragment

class BugReportDialogFragment: DataBindingDialogFragment<DialogBugReportBinding>(), MyPageContract.BugReportContract{
    override val layoutId: Int
        get() = R.layout.dialog_bug_report

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val factory = MyPageViewModelFactory(this)
        binding.myPageViewModel = ViewModelProviders.of(this, factory).get(MyPageViewModel::class.java)
        return rootView
    }

    override fun onStart() {
        super.onStart()
        dialog.window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }

    override fun exitBugReport() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}