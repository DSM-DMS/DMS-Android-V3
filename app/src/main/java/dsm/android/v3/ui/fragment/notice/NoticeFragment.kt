package dsm.android.v3.ui.fragment.notice

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import dsm.android.v3.R
import dsm.android.v3.databinding.FragmentNoticeMainBinding
import dsm.android.v3.presentation.di.scope.ActivityScope
import dsm.android.v3.presentation.di.scope.FragmentScope
import dsm.android.v3.presentation.viewModel.notice.NoticeViewModel
import dsm.android.v3.presentation.viewModel.notice.NoticeViewModelFactory
import dsm.android.v3.util.DataBindingFragment
import javax.inject.Inject

@ActivityScope
class NoticeFragment : DataBindingFragment<FragmentNoticeMainBinding>() {

    override val layoutId: Int
        get() = R.layout.fragment_notice_main

    @Inject
    lateinit var factory: NoticeViewModelFactory

    val viewModel by lazy { ViewModelProviders.of(activity!!, factory)[NoticeViewModel::class.java] }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        val viewModel = ViewModelProviders.of(activity!!, factory)[NoticeViewModel::class.java]
        binding.vm = viewModel
        viewModel.getNoticeListLiveEvent.observe(this, Observer {
            findNavController().navigate(
                NoticeFragmentDirections.actionNoticeFragmentToNoticeListFragment()
            )
        })
    }
}