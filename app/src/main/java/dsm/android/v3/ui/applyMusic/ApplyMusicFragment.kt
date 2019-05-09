package dsm.android.v3.ui.applyMusic

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.view.PagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import dsm.android.v3.R
import dsm.android.v3.databinding.FragmentApplyMusicBinding
import dsm.android.v3.model.ApplyPagerModel
import dsm.android.v3.util.DataBindingFragment
import kotlinx.android.synthetic.main.fragment_apply_music.*
import org.jetbrains.anko.find
import org.jetbrains.anko.support.v4.toast

class ApplyMusicFragment : DataBindingFragment<FragmentApplyMusicBinding>() {

    override val layoutId: Int
        get() = R.layout.fragment_apply_music

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModel = ViewModelProviders.of(activity!!).get(ApplyMusicViewModel::class.java)

        viewModel.actionMusicLogLiveEvent.observe(this, Observer {
            findNavController().navigate(ApplyMusicFragmentDirections.actionApplyMusicFragmentToApplyMusicLogFragment())
        })

        viewModel.toastLiveEvent.observe(this, Observer{ toast(it!!) })

        binding.viewModel = viewModel
        binding.applyMusicApplyListPager.adapter = ApplyMusicPageAdapter(viewModel)
        register(binding.viewModel!!)
    }
}
