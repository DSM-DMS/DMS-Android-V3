package dsm.android.v3.presentation.viewModel.mypage.logout

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.view.View
import dsm.android.v3.ui.activity.signIn.AuthDatabase
import dsm.android.v3.util.SingleLiveEvent
import org.jetbrains.anko.doAsync

class LogoutViewModel(): ViewModel(){

    val toastLiveData = MutableLiveData<String>()
    val intentToLoginEvent = SingleLiveEvent<Any>()
    val exitLogoutEvent = SingleLiveEvent<Any>()

    fun logoutClickCancel() = exitLogoutEvent.call()

    fun logoutClickLogout(view: View) {
        doAsync {
            val auth =  AuthDatabase.getInstance(view.context)!!.getAuthDao().getAuth()
            AuthDatabase.getInstance(view.context)!!.getAuthDao().delete(auth)
        }
        removeToken(view.context)
        intentToLoginEvent.call()
        toastLiveData.value = "로그아웃 하였습니다."
        exitLogoutEvent.call()
    }
}
