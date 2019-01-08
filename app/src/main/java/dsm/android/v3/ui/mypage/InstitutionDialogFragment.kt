package dsm.android.v3.ui.mypage

import android.arch.lifecycle.ViewModelProviders
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dsm.android.v3.R
import dsm.android.v3.databinding.DialogInstitutionReportBinding
import dsm.android.v3.util.DataBindingDialogFragment
import kotlinx.android.synthetic.main.dialog_institution_report.*

class InstitutionDialogFragment: DataBindingDialogFragment<DialogInstitutionReportBinding>(), MyPageContract.InstitutionReportContract{

    override val layoutId: Int
        get() = R.layout.dialog_institution_report

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

    override fun exitInstitutionReport() = dialog.dismiss()

    override fun flagInstitutionTitleBlankError() { institution_dialog_title_edit.error = "제목을 입력하세요."}
    override fun flagInstitutionRoomNumberBlankError() { institution_dialog_room_number_edit.error = "방 번호을 입력하세요." }
    override fun flagInstitutionContentBlankError() { institution_dialog_report_content_edit.error = "내용를 입력하세요." }
}