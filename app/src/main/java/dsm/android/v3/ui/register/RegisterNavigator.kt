package dsm.android.v3.ui.register

interface RegisterNavigator {
    fun success(message: String)
    fun fail(message: String)
    fun signUpPost()
}