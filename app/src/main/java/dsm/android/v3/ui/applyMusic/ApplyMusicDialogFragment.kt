package dsm.android.v3.ui.applyMusic

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.View
import dsm.android.v3.R
import dsm.android.v3.databinding.DialogApplyMusicBinding
import dsm.android.v3.util.DataBindingDialogFragment
import kotlinx.android.synthetic.main.dialog_apply_music.*
import org.jetbrains.anko.sdk27.coroutines.onClick

class ApplyMusicDialogFragment : DataBindingDialogFragment<DialogApplyMusicBinding>() {
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