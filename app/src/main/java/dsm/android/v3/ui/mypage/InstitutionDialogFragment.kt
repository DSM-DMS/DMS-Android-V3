package dsm.android.v3.ui.mypage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dsm.android.v3.R
import dsm.android.v3.databinding.DialogInstitutionReportBinding
import dsm.android.v3.util.DataBindingDialogFragment

class InstitutionDialogFragment: DataBindingDialogFragment<DialogInstitutionReportBinding>(), MyPageContract.InstitutionReportContract{
    override val layoutId: Int
        get() = R.layout.dialog_institution_report

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding.myPageViewModel = MyPageViewModel(this)
        return rootView
    }

    override fun exitInstitutionReport() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}