package dsm.android.v3.ui.fragment.applyMusic


import android.app.Application
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.View
import dsm.android.v3.R
import dsm.android.v3.data.remote.ApiClient
import dsm.android.v3.ui.adapter.ApplyMusicAdapter
import dsm.android.v3.databinding.FragmentApplyMusicLogBinding
import dsm.android.v3.domain.repository.applyMusic.ApplyMusicRepositoryImpl
import dsm.android.v3.presentation.viewModel.applyMusic.ApplyMusicViewModel
import dsm.android.v3.presentation.viewModel.applyMusic.ApplyMusicViewModelFactory
import dsm.android.v3.ui.dialogFragment.applyMusic.ApplyMusicDialogFragment
import dsm.android.v3.util.DataBindingFragment
import kotlinx.android.synthetic.main.fragment_apply_music_log.*
import javax.inject.Inject

class ApplyMusicLogFragment : DataBindingFragment<FragmentApplyMusicLogBinding>() {

    override val layoutId: Int
        get() = R.layout.fragment_apply_music_log

    @Inject
    lateinit var apiClient: ApiClient

    @Inject
    lateinit var application: Application

    val factory by lazy { ApplyMusicViewModelFactory(ApplyMusicRepositoryImpl(apiClient), application) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewModel = ViewModelProviders.of(activity!!, factory).get(ApplyMusicViewModel::class.java)
        binding.viewModel = viewModel
        apply_music_musicList_rv.adapter = ApplyMusicAdapter(viewModel)
        viewModel.dialogCallEvent.observe(this, Observer {
            ApplyMusicDialogFragment().show(fragmentManager, "ApplyMusicDialogFragment")
        })
        viewModel.dataSetChangedLiveEvent.observe(this, Observer {
            apply_music_musicList_rv.adapter!!.notifyDataSetChanged()
        })
    }


}
