package dsm.android.v3.ui.applyGoingLog

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import dsm.android.v3.R
import dsm.android.v3.adapter.ApplyGoingLogAdapter
import dsm.android.v3.databinding.ActivityApplyGoingLogBinding
import dsm.android.v3.model.ApplyGoingModel
import dsm.android.v3.ui.applyGoingLog.ApplyGoingLogData.deleteDataList
import dsm.android.v3.util.DataBindingActivity
import org.jetbrains.anko.toast

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

    override fun logItemClickTrue(model: ApplyGoingModel.ApplyGoingDataModel) {
        deleteDataList.remove(model)
        if(deleteDataList.isEmpty()) invisibleDeleteBtn()
    }

    override fun logItemClickFalse(model: ApplyGoingModel.ApplyGoingDataModel) {
        deleteDataList.add(model)
        visibleDeleteBtn()
    }

    override fun createShortToast(text: String) = toast(text).show()


    override fun setApplyList(models: ArrayList<ApplyGoingModel.ApplyGoingDataModel>) {
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