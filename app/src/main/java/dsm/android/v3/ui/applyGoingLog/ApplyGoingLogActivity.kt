package dsm.android.v3.ui.applyGoingLog

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import dsm.android.v3.R
import dsm.android.v3.adapter.ApplyGoingLogAdapter
import dsm.android.v3.databinding.ActivityApplyGoingLogBinding
import dsm.android.v3.model.ApplyGoingModel
import dsm.android.v3.ui.applyGoingEdit.ApplyGoingEditActivity
import dsm.android.v3.ui.applyGoingLog.ApplyGoingLogData.deleteItem
import dsm.android.v3.util.DataBindingActivity
import org.jetbrains.anko.startActivity

class ApplyGoingLogActivity: DataBindingActivity<ActivityApplyGoingLogBinding>(), ApplyGoingLogContract, ApplyGoingLogContract.ApplyGoingLogRv {

    override val layoutId: Int
        get() = R.layout.activity_apply_going_log

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val factory = ApplyGoingLogViewModelFactory(this, intent.getStringExtra("title"))
        binding.applyGoingApplyRecordRv.layoutManager = LinearLayoutManager(this)
        binding.applyGoingLogViewModel = ViewModelProviders.of(this, factory).get(ApplyGoingLogViewModel::class.java)
    }

    override fun logItemClick(model: ApplyGoingModel.ApplyGoingDataModel) {
        deleteItem = model
        startActivity<ApplyGoingEditActivity>()
        finish()
    }

    override fun setApplyList(models: ArrayList<ApplyGoingModel.ApplyGoingDataModel>) {
        binding.applyGoingApplyRecordRv.adapter = ApplyGoingLogAdapter(models, this)
    }

    override fun backApplyGoing() = finish()
}