package dsm.android.v3.ui.applyMusicLog

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import dsm.android.v3.R
import dsm.android.v3.databinding.ActivityApplyMusicLogBinding
import dsm.android.v3.util.DataBindingActivity

class ApplyMusicLogActivity: DataBindingActivity<ActivityApplyMusicLogBinding>(), ApplyMusicLogContract {
    override val layoutId: Int
        get() = R.layout.activity_apply_music_log

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val factory = ApplyMusicLogViewModelFactory(this)
        binding.applyMusicLogViewModel = ViewModelProviders.of(this,  factory).get(ApplyMusicLogViewModel::class.java)

    }

}