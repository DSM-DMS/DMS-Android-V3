package dsm.android.v3.ui.applyMusic


import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.View
import dsm.android.v3.R
import dsm.android.v3.databinding.FragmentApplyMusicLogBinding
import dsm.android.v3.util.DataBindingFragment

class ApplyMusicLogFragment : DataBindingFragment<FragmentApplyMusicLogBinding>() {

    override val layoutId: Int
        get() = R.layout.fragment_apply_music_log

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewModel = ViewModelProviders.of(activity!!).get(ApplyMusicViewModel::class.java)
        binding.viewModel = viewModel
        viewModel.dialogCallEvent.observe(this, Observer {
            ApplyMusicDialogFragment().show(fragmentManager, "시ㅡ발")
//            findNavController().navigate(ApplyMusicLogFragmentDirections.actionApplyMusicLogFragmentToApplyMusicDialogFragment())
        })
    }


}
