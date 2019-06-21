package dsm.android.v3.ui.dialogFragment.applyMusic

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.DialogInterface
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import dsm.android.v3.R
import dsm.android.v3.databinding.DialogApplyMusicBinding
import dsm.android.v3.presentation.di.scope.FragmentScope
import dsm.android.v3.presentation.viewModel.applyMusic.ApplyMusicViewModel
import dsm.android.v3.presentation.viewModel.applyMusic.ApplyMusicViewModelFactory
import dsm.android.v3.util.DataBindingDialogFragment
import kotlinx.android.synthetic.main.dialog_apply_music.*
import org.jetbrains.anko.sdk27.coroutines.onClick
import javax.inject.Inject

@FragmentScope
class ApplyMusicDialogFragment : DataBindingDialogFragment<DialogApplyMusicBinding>() {
    override val layoutId: Int
        get() = R.layout.dialog_apply_music

    @Inject
    lateinit var factory: ApplyMusicViewModelFactory

    val viewModel by lazy {
        ViewModelProviders.of(activity!!, factory)[ApplyMusicViewModel::class.java]
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        music_dialog_cancel_tv.onClick { dismiss() }
        viewModel.fragmentDismissLiveEvent.observe(this, Observer {
            dismiss()
        })
        binding.viewModel = viewModel
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }

    override fun onDismiss(dialog: DialogInterface?) {
        super.onDismiss(dialog)
        viewModel.inputArtistLiveData.value = ""
        viewModel.inputMusicLiveData.value = ""

    }
}