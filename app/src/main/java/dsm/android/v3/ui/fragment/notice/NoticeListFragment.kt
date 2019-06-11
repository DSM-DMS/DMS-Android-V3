package dsm.android.v3.ui.fragment.notice

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.design.widget.BottomSheetBehavior
import android.text.method.ScrollingMovementMethod
import android.view.View
import androidx.navigation.fragment.findNavController
import dsm.android.v3.R
import dsm.android.v3.ui.adapter.NoticeAdapter
import dsm.android.v3.databinding.FragmentNoticeListBinding
import dsm.android.v3.presentation.di.scope.ActivityScope
import dsm.android.v3.presentation.di.scope.FragmentScope
import dsm.android.v3.presentation.viewModel.notice.NoticeViewModel
import dsm.android.v3.presentation.viewModel.notice.NoticeViewModelFactory
import dsm.android.v3.util.DataBindingFragment
import javax.inject.Inject

@ActivityScope
class NoticeListFragment : DataBindingFragment<FragmentNoticeListBinding>() {

    override val layoutId: Int
        get() = R.layout.fragment_notice_list

    @Inject
    lateinit var factory: NoticeViewModelFactory

    val viewModel by lazy { ViewModelProviders.of(activity!!, factory)[NoticeViewModel::class.java] }
    
    val bottomSheetBehavior by lazy { BottomSheetBehavior.from(binding.noticeDescriptionBottomSheet) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.vm = viewModel
        binding.noticeListRv.adapter = NoticeAdapter(viewModel)
        binding.noticeDescriptionContentTv.movementMethod = ScrollingMovementMethod()
        viewModel.getDescriptionLiveEvent.observe(this, Observer {
            //            NoticeDescriptionFragment().show(fragmentManager!!, "")
            bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
        })
        bottomSheetBehavior.let {
            it.isHideable = true
            it.state = BottomSheetBehavior.STATE_HIDDEN
        }

        viewModel.finishNoticeListLiveEvent.observe(this, Observer {
            findNavController().popBackStack()
        })

        viewModel.closeDescriptionLiveEvent.observe(this, Observer {
            bottomSheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN
        })
    }
}
