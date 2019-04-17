package dsm.android.v3.ui.notice


import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.design.widget.BottomSheetBehavior
import android.view.View
import androidx.navigation.fragment.findNavController

import dsm.android.v3.R
import dsm.android.v3.adapter.notice.NoticeAdapter
import dsm.android.v3.databinding.FragmentNoticeListBinding
import dsm.android.v3.util.DataBindingFragment

class NoticeListFragment : DataBindingFragment<FragmentNoticeListBinding>() {

    override val layoutId: Int
        get() = R.layout.fragment_notice_list

    val viewModel by lazy { ViewModelProviders.of(activity!!)[NoticeViewModel::class.java] }
    val bottomSheetBehavior by lazy { BottomSheetBehavior.from(binding.noticeDescriptionBottomSheet) }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.vm = viewModel
        binding.noticeListRv.adapter = NoticeAdapter(viewModel)
        viewModel.getDescriptionLiveEvent.observe(this, Observer {
            //            NoticeDescriptionFragment().show(fragmentManager!!, "")
            bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
        })
        bottomSheetBehavior.let {
            it.isHideable = true
            it.state = BottomSheetBehavior.STATE_HIDDEN
        }
    }

    fun onBackPressed() {
        BottomSheetBehavior.from(binding.noticeDescriptionBottomSheet).let {
            if (it.state == BottomSheetBehavior.STATE_EXPANDED || it.state == BottomSheetBehavior.STATE_HALF_EXPANDED) {
                it.state = BottomSheetBehavior.STATE_HIDDEN
            }
        }
    }
}
