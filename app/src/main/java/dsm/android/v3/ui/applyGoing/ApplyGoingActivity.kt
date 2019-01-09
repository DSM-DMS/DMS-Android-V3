package dsm.android.v3.ui.applyGoing

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import dsm.android.v3.R
import dsm.android.v3.databinding.ActivityApplyGoingBinding
import dsm.android.v3.util.DataBindingActivity
import kotlinx.android.synthetic.main.activity_apply_going.*

class ApplyGoingActivity: DataBindingActivity<ActivityApplyGoingBinding>(), ApplyGoingContract{
    override val layoutId: Int
        get() = R.layout.activity_apply_going

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val factory = ApplyGoingViewModelFactory(this)
        binding.applyGoingViewModel = ViewModelProviders.of(this, factory).get(ApplyGoingViewModel::class.java)
        applyGoing_apply_list_pager.adapter = ApplyPageAdapter(supportFragmentManager)
        applyGoing_apply_list_pager.currentItem = 0
    }

    inner class ApplyPageAdapter(fm: FragmentManager?) : FragmentPagerAdapter(fm) {

        override fun getItem(position: Int): Fragment? {
            when (position){
                0 -> return ApplyGoingSaturdayFragment()
                1 -> return ApplyGoingSundayFragment()
                2 -> return ApplyGoingWorkdayFragment()
                else -> return null
            }
        }
        override fun getCount(): Int = 3
    }
}