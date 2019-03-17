package dsm.android.v3.ui.logOutDialog

import android.arch.lifecycle.ViewModel
import android.content.Context
import android.view.View
import dsm.android.v3.util.removeToken

class LogOutViewModel(val contract: LogOutContract): ViewModel(){

    fun logoutClickCancel() = contract.exitLogout()

    fun logoutClickLogout(view: View) {
        removeToken(view.context)
        contract.createShortToast("로그아웃 하였습니다.")
        contract.exitLogout()
    }
}
