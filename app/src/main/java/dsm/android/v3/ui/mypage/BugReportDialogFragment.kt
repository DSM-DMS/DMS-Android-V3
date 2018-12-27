package dsm.android.v3.ui.mypage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dsm.android.v3.R
import dsm.android.v3.databinding.DialogBugReportBinding
import dsm.android.v3.util.DataBindingDialogFragment

class BugReportDialogFragment: DataBindingDialogFragment<DialogBugReportBinding>(), MyPageContract.BugReportContract{
    override val layoutId: Int
        get() = R.layout.dialog_logout

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding.myPageViewModel = MyPageViewModel(this)
        rootView = binding.root
        return rootView
    }

    override fun exitBugReport() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}