package dsm.android.v3.ui.applyGoingDoc

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import dsm.android.v3.R
import dsm.android.v3.databinding.ActivityApplyGoingDocBinding
import dsm.android.v3.util.DataBindingActivity
import kotlinx.android.synthetic.main.activity_apply_going_doc.*
import org.jetbrains.anko.toast

class ApplyGoingDocActivity: DataBindingActivity<ActivityApplyGoingDocBinding>(),
    ApplyGoingDocContract {
    override val layoutId: Int
        get() = R.layout.activity_apply_going_doc

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val factory = ApplyGoingDocViewModelFactory(this)
        binding.applyGoingDocViewModel = ViewModelProviders.of(this, factory).get(ApplyGoingDocViewModel::class.java)
    }

    override fun createShortToast(text: String) = toast(text).show()

    override fun backApplyGoing() = finish()

    override fun setErrorApplyGoingGoDate() { applyGoing_doc_going_date_edit.error = "MM/DD 형식에 맞게 입력하세요." }

    override fun setErrorApplyGoingGoTime() { applyGoing_doc_going_time_edit.error = "HH/MM ~ HH/MM 형식에 맞게 입력하세요." }

    override fun setErrorApplyGoingReason() { applyGoing_doc_reason_content_edit.error = "사유를 입력하세요." }
}