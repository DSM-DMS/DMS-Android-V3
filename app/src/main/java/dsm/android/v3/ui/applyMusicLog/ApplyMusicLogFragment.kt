package dsm.android.v3.ui.applyMusicLog


import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.view.View
import androidx.navigation.fragment.findNavController
import dsm.android.v3.R
import dsm.android.v3.databinding.FragmentApplyMusicLogBinding
import dsm.android.v3.ui.applyMusic.ApplyMusicViewModel
import dsm.android.v3.ui.applyMusicDialog.ApplyMusicDialogFragment
import dsm.android.v3.util.DataBindingFragment
import kotlinx.android.synthetic.main.fragment_apply_music_log.*
import org.jetbrains.anko.textColor

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
