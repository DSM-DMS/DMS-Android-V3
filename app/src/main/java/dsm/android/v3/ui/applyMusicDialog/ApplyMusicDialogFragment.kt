package dsm.android.v3.ui.applyMusicDialog

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dsm.android.v3.R
import dsm.android.v3.databinding.DialogApplyMusicBinding
import dsm.android.v3.util.DataBindingDialogFragment

class ApplyMusicDialogFragment: DataBindingDialogFragment<DialogApplyMusicBinding>(), ApplyMusicDialogContract{
    override val layoutId: Int
        get() = R.layout.dialog_apply_music

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val factory = ApplyMusicDialogViewModelFactory(this)
        binding.applyMusicDialogViewModel = ViewModelProviders.of(this, factory).get(ApplyMusicDialogViewModel::class.java)
        return rootView
    }

}