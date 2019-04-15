package dsm.android.v3.ui.changePassword

import android.arch.lifecycle.*
import android.view.View
import android.widget.Toast
import com.google.gson.JsonObject
import dsm.android.v3.connecter.Connecter
import dsm.android.v3.database.Auth
import dsm.android.v3.ui.signIn.AuthDatabase
import dsm.android.v3.util.SingleLiveEvent
import dsm.android.v3.util.getToken
import org.jetbrains.anko.doAsync
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ChangePasswordViewModel : ViewModel() {

    val currentPassword = MutableLiveData<String>()
    val newPassword = MutableLiveData<String>()
    val confirmPassword = MutableLiveData<String>()
    val activityFinishLiveEvent = SingleLiveEvent<Any>()
    val inputStatus = MediatorLiveData<Boolean>().apply {
        addSource(currentPassword) {
            this.value = !currentPassword.isValueBlank() && newPassword.value == confirmPassword.value &&
                    !newPassword.isValueBlank() && !confirmPassword.isValueBlank()
        }
        addSource(newPassword) {
            this.value = !currentPassword.isValueBlank() && newPassword.value == confirmPassword.value &&
                    !newPassword.isValueBlank() && !confirmPassword.isValueBlank()
        }
        addSource(confirmPassword) {
            this.value = !currentPassword.isValueBlank() && newPassword.value == confirmPassword.value &&
                    !newPassword.isValueBlank() && !confirmPassword.isValueBlank()
        }
    }

    fun changePassword(view: View) {
        val json = JsonObject().apply {
            addProperty("currentPassword", currentPassword.value)
            addProperty("newPassword", newPassword.value)
        }

        Connecter.api.changePw(getToken(view.context), json).enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                when (response.code()) {
                    201 -> {
                        Toast.makeText(view.context, "비밀번호 변경이 완료되었습니다.", Toast.LENGTH_SHORT).show()
                        doAsync {
                            val instance = AuthDatabase.getInstance(view.context)!!
                                .getAuthDao()
                            val origin = instance.getAuth()
                            instance.insert(Auth(origin.id, newPassword.value!!))

                        }
                        activityFinishLiveEvent.call()
                    }
                    205 -> {
                        Toast.makeText(view.context, "현재 비밀번호와 새 비밀번호가 동일합니다.", Toast.LENGTH_SHORT).show()
                    }
                    403 -> {
                        Toast.makeText(view.context, "비밀번호 변경이 제대로 완료되지 않았습니다.", Toast.LENGTH_SHORT).show()
                        activityFinishLiveEvent.call()
                    }
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                Toast.makeText(view.context, "비밀번호 변경이 제대로 완료되지 않았습니다.", Toast.LENGTH_SHORT).show()
                activityFinishLiveEvent.call()
            }

        })
    }

    fun onCloseBtnClicked() {
        activityFinishLiveEvent.call()
    }

    fun MutableLiveData<String>.isValueBlank() =
        this.value.isNullOrBlank()


}