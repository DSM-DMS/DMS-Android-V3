package dsm.android.v3.presentation.viewModel.mypage.logout

import android.arch.lifecycle.MutableLiveData
import android.view.View
import dsm.android.v3.domain.repository.mypage.MyPageRepository
import dsm.android.v3.util.BaseViewModel
import dsm.android.v3.util.SingleLiveEvent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LogoutViewModel(val myPageRepository: MyPageRepository): BaseViewModel(){

    val toastLiveData = MutableLiveData<String>()
    val intentToLoginEvent = SingleLiveEvent<Any>()
    val exitLogoutEvent = SingleLiveEvent<Any>()

    fun logoutClickCancel() = exitLogoutEvent.call()

    fun logoutClickLogout(view: View) {
        CoroutineScope(Dispatchers.IO).launch { myPageRepository.logout() }
        intentToLoginEvent.call()
        toastLiveData.value = "로그아웃 하였습니다."
        exitLogoutEvent.call()
    }
}
