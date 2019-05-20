package dsm.android.v3.presentation.viewModel.mypage

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import dsm.android.v3.domain.repository.mypage.MyPageRepository

class MyPageViewModelFactory(val myPageRepository: MyPageRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
            modelClass.getConstructor(MyPageRepository::class.java).newInstance(myPageRepository)
}