package dsm.android.v3.ui.applyGoingLog

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import dsm.android.v3.R
import dsm.android.v3.adapter.ApplyGoingLogAdapter
import dsm.android.v3.databinding.ActivityApplyGoingLogBinding
import dsm.android.v3.model.ApplyGoingLogItemModel
import dsm.android.v3.ui.applyGoing.ApplyGoingContract
import dsm.android.v3.ui.applyGoingLog.ApplyGoingLogData.deleteData
import dsm.android.v3.ui.applyGoing.ApplyGoingViewModel
import dsm.android.v3.ui.applyGoing.ApplyGoingViewModelFactory
import dsm.android.v3.util.DataBindingActivity

class ApplyGoingLogActivity: DataBindingActivity<ActivityApplyGoingLogBinding>(), ApplyGoingLogContract, ApplyGoingLogContract.ApplyGoingLogRv {

    override val layoutId: Int
        get() = R.layout.activity_apply_going_log

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val factory = ApplyGoingLogViewModelFactory(this, intent.getStringExtra("title"))
        binding.applyGoingApplyRecordRv.layoutManager = LinearLayoutManager(this)
        binding.applyGoingLogViewModel = ViewModelProviders.of(this, factory).get(ApplyGoingLogViewModel::class.java)
        invisibleDeleteBtn()
    }

    override fun logItemClickTrue(model: ApplyGoingLogItemModel) {
        deleteData.remove(model)
        if(deleteData.isEmpty()){
            invisibleDeleteBtn()
        }
    }

    override fun logItemClickFalse(model: ApplyGoingLogItemModel) {
        deleteData.add(model)
        visibleDeleteBtn()
    }


    override fun setApplyList(models: ArrayList<ApplyGoingLogItemModel>) {
        binding.applyGoingApplyRecordRv.adapter = ApplyGoingLogAdapter(models, this)
    }

    override fun backApplyGoing() = finish()

    fun visibleDeleteBtn() {
        binding.applyGoingApplyDeleteBtn.visibility = View.VISIBLE
    }

    fun invisibleDeleteBtn() {
        binding.applyGoingApplyDeleteBtn.visibility = View.INVISIBLE
    }
}