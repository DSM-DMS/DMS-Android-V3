package dsm.android.v3.ui.signIn

interface SignInNavigator {
    fun intentToRegister()
    fun intentToMain()
    fun showToast(message: String)
}