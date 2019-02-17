package dsm.android.v3.connecter

import android.content.*
import android.widget.Toast
import dsm.android.v3.util.*
import retrofit2.*

/**
 * Created by root1 on 2017. 11. 23..
 */
abstract class Res<T>(val context: Context, val check401: Boolean = true): Callback<T> {

    abstract fun callBack(code: Int, body: T?)

    override fun onFailure(call: Call<T>?, t: Throwable?) {
        showToast("네크워크 오류")
    }

    override fun onResponse(call: Call<T>?, response: Response<T>) {
        val code = response.code()
        val body = response.body()

        if (code == 401 && check401){
            showToast("다시 로그인 하세요")
            removeToken(context)
        }

        when(code){
            500 -> showToast("서버 오류")
            422 -> { showToast("로그인이 필요합니다")
                removeToken(context) }
            403 -> showToast("권한이 없습니다")
            else -> callBack(code, body)
        }

    }
    fun showToast(message : String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

}