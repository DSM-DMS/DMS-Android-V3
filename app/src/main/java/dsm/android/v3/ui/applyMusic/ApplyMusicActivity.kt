package dsm.android.v3.ui.applyMusic

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import dsm.android.v3.R
import dsm.android.v3.databinding.ActivityApplyMusicBinding
import dsm.android.v3.util.DataBindingActivity

class ApplyMusicActivity: DataBindingActivity<ActivityApplyMusicBinding>(), ApplyMusicContract {
    override val layoutId: Int
        get() = R.layout.activity_apply_music

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val factory = ApplyMusicViewModelFactory(this)
        binding.applyMusicViewModel = ViewModelProviders.of( this, factory).get(ApplyMusicViewModel::class.java)

    }

}