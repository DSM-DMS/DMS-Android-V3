package dsm.android.v3.ui.fragment.putIn


import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dsm.android.v3.R
import dsm.android.v3.databinding.FragmentPutInBinding
import dsm.android.v3.presentation.di.app.BaseApp
import dsm.android.v3.presentation.di.scope.ActivityScope
import dsm.android.v3.presentation.viewModel.putIn.PutInViewModel
import dsm.android.v3.ui.activity.applyExtensionStudy.ApplyExtensionStudyActivity
import dsm.android.v3.ui.activity.applyGoingOut.ApplyGoingOutActivity
import dsm.android.v3.ui.activity.applyMusic.ApplyMusicDomActivity
import dsm.android.v3.ui.activity.applyStaying.ApplyStayingActivity
import dsm.android.v3.util.DataBindingFragment
import org.jetbrains.anko.support.v4.startActivity

@ActivityScope
class PutInFragment : DataBindingFragment<FragmentPutInBinding>() {
    override val layoutId: Int
        get() = R.layout.fragment_put_in

    val viewModel by lazy { ViewModelProviders.of(this)[PutInViewModel::class.java] }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        super.onCreateView(inflater, container, savedInstanceState)
        binding.vm = viewModel

        viewModel.extensionLiveEvent.observe(this, Observer {
            startActivity<ApplyExtensionStudyActivity>()
        })
        viewModel.stayLiveEvent.observe(this, Observer {
            startActivity<ApplyStayingActivity>()
        })
        viewModel.musicLiveEvent.observe(this, Observer {
            startActivity<ApplyMusicDomActivity>()
        })
        viewModel.goingOutLiveEvent.observe(this, Observer {
            startActivity<ApplyGoingOutActivity>()
        })
        return rootView
    }


}
