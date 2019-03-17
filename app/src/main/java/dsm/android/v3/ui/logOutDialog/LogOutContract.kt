package dsm.android.v3.ui.logOutDialog

interface LogOutContract{
    fun intentToLogin()
    fun exitLogout()
    fun createShortToast(text: String)
}