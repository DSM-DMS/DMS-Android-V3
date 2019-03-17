package dsm.android.v3.ui.logOutDialog

import android.arch.lifecycle.ViewModel
import android.content.Context
import android.view.View
import dsm.android.v3.ui.signIn.AuthDatabase
import dsm.android.v3.util.removeToken
import org.jetbrains.anko.doAsync

class LogOutViewModel(val contract: LogOutContract): ViewModel(){

    fun logoutClickCancel() = contract.exitLogout()

    fun logoutClickLogout(view: View) {
        doAsync {
            val auth =  AuthDatabase.getInstance(view.context)!!.getAuthDao().getAuth()
            AuthDatabase.getInstance(view.context)!!.getAuthDao().delete(auth)
        }
        removeToken(view.context)
        contract.intentToLogin()
        contract.createShortToast("로그아웃 하였습니다.")
        contract.exitLogout()
    }
}
