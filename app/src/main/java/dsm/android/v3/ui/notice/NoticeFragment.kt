package dsm.android.v3.ui.notice

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import dsm.android.v3.R
import dsm.android.v3.databinding.FragmentNoticeMainBinding
import dsm.android.v3.ui.customView.CustomCardView
import dsm.android.v3.util.DataBindingFragment
import org.jetbrains.anko.find

class NoticeFragment : DataBindingFragment<FragmentNoticeMainBinding>() {

    override val layoutId: Int
        get() = R.layout.fragment_notice_main
    val viewModel by lazy { ViewModelProviders.of(activity!!)[NoticeViewModel::class.java] }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.vm = viewModel
        viewModel.getNoticeListLiveEvent.observe(this, Observer {
            findNavController().navigate(
                NoticeFragmentDirections.actionNoticeFragmentToNoticeListFragment()
            )
        })
    }

}