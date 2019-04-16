package dsm.android.v3.ui.applyGoingOut.applyGoingLog

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import dsm.android.v3.R
import dsm.android.v3.adapter.ApplyGoingLogAdapter
import dsm.android.v3.databinding.ActivityApplyGoingLogBinding
import dsm.android.v3.ui.applyGoingOut.applyGoingEdit.ApplyGoingEditActivity
import dsm.android.v3.util.DataBindingActivity
import kotlinx.android.synthetic.main.activity_apply_going_log.*
import org.jetbrains.anko.startActivity

class ApplyGoingLogActivity: DataBindingActivity<ActivityApplyGoingLogBinding>() {

    override val layoutId: Int
        get() = R.layout.activity_apply_going_log

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setSupportActionBar(applyGoing_log_toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        title = intent.getStringExtra("title")

        applyGoing_log_toolbar.setNavigationOnClickListener { onBackPressed() }

        val viewModelFactory = ApplyGoingLogViewModelFactory(intent.getStringExtra("title"))
        val viewModel = ViewModelProviders.of(this, viewModelFactory).get(ApplyGoingLogViewModel::class.java)

        viewModel.logItemClickSingleLiveEvent.observe(this, Observer {
            startActivity<ApplyGoingEditActivity>()
            finish()
        })

        binding.applyGoingApplyRecordRv.layoutManager = LinearLayoutManager(this)
        binding.applyGoingApplyRecordRv.adapter = ApplyGoingLogAdapter()
        binding.applyGoingLogViewModel = viewModel
    }
}