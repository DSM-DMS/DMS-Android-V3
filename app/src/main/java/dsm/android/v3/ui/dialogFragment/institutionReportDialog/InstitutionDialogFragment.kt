package dsm.android.v3.ui.dialogFragment.institutionReportDialog

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dsm.android.v3.R
import dsm.android.v3.databinding.DialogInstitutionReportBinding
import dsm.android.v3.presentation.di.scope.FragmentScope
import dsm.android.v3.presentation.viewModel.mypage.institutionReport.InstitutionReportViewModel
import dsm.android.v3.presentation.viewModel.mypage.institutionReport.InstitutionReportViewModelFactory
import dsm.android.v3.util.DataBindingDialogFragment
import org.jetbrains.anko.support.v4.toast
import javax.inject.Inject

@FragmentScope
class InstitutionDialogFragment: DataBindingDialogFragment<DialogInstitutionReportBinding>() {

    override val layoutId: Int
        get() = R.layout.dialog_institution_report

    @Inject
    lateinit var factory: InstitutionReportViewModelFactory

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val viewModel = ViewModelProviders.of(this, factory).get(InstitutionReportViewModel::class.java)

        viewModel.toastLiveData.observe(this, Observer { toast(it!!) })
        viewModel.exitInstitutionReportEvent.observe(this, Observer { dialog.dismiss() })

        binding.institutionReportViewModel = viewModel
        return rootView
    }

    override fun onStart() {
        super.onStart()
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }
}