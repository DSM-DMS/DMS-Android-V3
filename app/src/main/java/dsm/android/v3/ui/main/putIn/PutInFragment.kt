package dsm.android.v3.ui.main.putIn


import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dsm.android.v3.R
import dsm.android.v3.databinding.FragmentPutInBinding
import dsm.android.v3.ui.applyMusicDom.ApplyMusicDomActivity
import dsm.android.v3.util.DataBindingFragment
import org.jetbrains.anko.support.v4.startActivity
import org.jetbrains.anko.support.v4.toast

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
//            startActivity<>()
//            TODO("연장신청 액티비티 연결하기")
        })
        viewModel.stayLiveEvent.observe(this, Observer {
//            startActivity<>()
//            TODO("잔류신청 액티비티 연결하기")
        })
        viewModel.musicLiveEvent.observe(this, Observer {
            startActivity<ApplyMusicDomActivity>()
//            TODO("기상음악 신청 액티비티 연결하기")
        })
        viewModel.goingOutLiveEvent.observe(this, Observer {
//            startActivity<>()
//            TODO("외출신청 액티비티 연결하기")
        })
        return rootView
    }


}
