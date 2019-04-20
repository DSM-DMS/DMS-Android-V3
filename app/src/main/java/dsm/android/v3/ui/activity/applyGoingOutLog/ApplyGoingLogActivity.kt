package dsm.android.v3.ui.activity.applyGoingOutLog

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import dsm.android.v3.R
import dsm.android.v3.ui.adapter.ApplyGoingLogAdapter
import dsm.android.v3.databinding.ActivityApplyGoingLogBinding
import dsm.android.v3.data.entity.ApplyGoingOutModel
import dsm.android.v3.presentation.viewModel.applyGoingOutLog.ApplyGoingLogViewModel
import dsm.android.v3.ui.activity.applyGoingOutEdit.ApplyGoingEditActivity
import dsm.android.v3.ui.activity.applyGoingOutLog.ApplyGoingLogData.deleteItem
import dsm.android.v3.util.DataBindingActivity
import kotlinx.android.synthetic.main.activity_apply_going_log.*
import org.jetbrains.anko.startActivity

class ApplyGoingLogActivity: DataBindingActivity<ActivityApplyGoingLogBinding>(), ApplyGoingLogContract, ApplyGoingLogContract.ApplyGoingLogRv {

    override val layoutId: Int
        get() = R.layout.activity_apply_going_log

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(applyGoing_log_toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true);
        supportActionBar?.setDisplayShowHomeEnabled(true);
        title = intent.getStringExtra("title")
        applyGoing_log_toolbar.setNavigationOnClickListener {
            onBackPressed()
        }
        val factory = ApplyGoingLogViewModelFactory(this, intent.getStringExtra("title"))
        binding.applyGoingApplyRecordRv.layoutManager = LinearLayoutManager(this)
        binding.applyGoingLogViewModel = ViewModelProviders.of(this, factory).get(ApplyGoingLogViewModel::class.java)
    }

    override fun logItemClick(model: ApplyGoingOutModel.ApplyGoingDataModel) {
        deleteItem = model
        startActivity<ApplyGoingEditActivity>()
        finish()
    }

    override fun setApplyList(models: ArrayList<ApplyGoingOutModel.ApplyGoingDataModel>) {
        binding.applyGoingApplyRecordRv.adapter = ApplyGoingLogAdapter(models, this)
    }
}