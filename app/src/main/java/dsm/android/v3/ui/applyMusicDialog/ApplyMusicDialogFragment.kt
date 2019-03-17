package dsm.android.v3.ui.applyMusicDialog

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dsm.android.v3.R
import dsm.android.v3.databinding.DialogApplyMusicBinding
import dsm.android.v3.ui.applyMusic.ApplyMusicViewModel
import dsm.android.v3.util.DataBindingDialogFragment
import kotlinx.android.synthetic.main.dialog_apply_music.*
import org.jetbrains.anko.sdk27.coroutines.onClick

class ApplyMusicDialogFragment : DataBindingDialogFragment<DialogApplyMusicBinding>(), ApplyMusicDialogContract {
    override val layoutId: Int
        get() = R.layout.dialog_apply_music

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewModel = ViewModelProviders.of(activity!!)[ApplyMusicViewModel::class.java]
        music_dialog_cancel_tv.onClick { dismiss() }
        viewModel.fragmentDismissLiveEvent.observe(this, Observer {
            dismiss()
        })
        binding.viewModel = viewModel

    }
}