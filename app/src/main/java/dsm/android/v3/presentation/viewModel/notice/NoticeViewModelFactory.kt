package dsm.android.v3.presentation.viewModel.notice

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import dsm.android.v3.domain.repository.notice.NoticeRepository

class NoticeViewModelFactory(val noticeRepository: NoticeRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        modelClass.getConstructor(NoticeRepository::class.java).newInstance(noticeRepository)


}