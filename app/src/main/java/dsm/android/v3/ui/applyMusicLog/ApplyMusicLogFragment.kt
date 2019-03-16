package dsm.android.v3.ui.applyMusicLog


import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController

import dsm.android.v3.R
import dsm.android.v3.databinding.FragmentApplyMusicBinding
import dsm.android.v3.databinding.FragmentApplyMusicLogBinding
import dsm.android.v3.ui.applyMusic.ApplyMusicViewModel
import dsm.android.v3.util.DataBindingFragment

class ApplyMusicLogFragment : DataBindingFragment<FragmentApplyMusicLogBinding>() {

    override val layoutId: Int
        get() = R.layout.fragment_apply_music_log

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewModel = ViewModelProviders.of(activity!!).get(ApplyMusicViewModel::class.java)
        binding.viewModel = viewModel

    }


}
