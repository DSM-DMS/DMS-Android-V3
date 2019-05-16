package dsm.android.v3.ui.dialogFragment.bugReportDialog

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dsm.android.v3.R
import dsm.android.v3.data.local.dao.AuthDao
import dsm.android.v3.data.local.shared.LocalStorage
import dsm.android.v3.data.remote.ApiClient
import dsm.android.v3.databinding.DialogBugReportBinding
import dsm.android.v3.domain.repository.mypage.MyPageRepositoryImpl
import dsm.android.v3.presentation.di.app.BaseApp
import dsm.android.v3.presentation.di.scope.ActivityScope
import dsm.android.v3.presentation.di.scope.FragmentScope
import dsm.android.v3.presentation.viewModel.mypage.MyPageViewModelFactory
import dsm.android.v3.presentation.viewModel.mypage.bugReport.BugReportViewModel
import dsm.android.v3.presentation.viewModel.mypage.bugReport.BugReportViewModelFactory
import dsm.android.v3.util.DataBindingDialogFragment
import org.jetbrains.anko.support.v4.toast
import javax.inject.Inject

@FragmentScope
class BugReportDialogFragment: DataBindingDialogFragment<DialogBugReportBinding>(){

    override val layoutId: Int
        get() = R.layout.dialog_bug_report

    @Inject
    lateinit var factory: BugReportViewModelFactory

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val viewModel = ViewModelProviders.of(this, factory).get(BugReportViewModel::class.java)

        viewModel.toastLiveData.observe(this, Observer { toast(it!!) })
        viewModel.exitBugReportEvent.observe(this, Observer { dialog.dismiss() })

        binding.bugReportViewModel = viewModel
        return rootView
    }

    override fun onStart() {
        super.onStart()
        dialog.window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }
}