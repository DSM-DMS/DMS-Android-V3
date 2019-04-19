package dsm.android.v3.ui.activity.signIn

interface SignInNavigator {
    fun intentToRegister()
    fun intentToMain()
    fun showToast(message: String)
}