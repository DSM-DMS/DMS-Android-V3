package dsm.android.v3.data.remote.interceptor

import android.util.Log
import dsm.android.v3.ui.activity.signIn.AuthDatabase
import dsm.android.v3.util.App
import dsm.android.v3.util.getToken
import dsm.android.v3.util.removeToken
import dsm.android.v3.util.saveToken
import okhttp3.Interceptor
import okhttp3.MediaType
import okhttp3.RequestBody
import okhttp3.Response
import org.bouncycastle.jcajce.provider.digest.SHA3
import org.bouncycastle.util.encoders.Hex
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.util.*

class SecretHeaderInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val userAgent = "okhttp/3.12.0"
        val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS", Locale.KOREA)
        val time = dateFormat.format(Calendar.getInstance().time)
        val base64 = org.bouncycastle.util.encoders.Base64.encode("$userAgent$time".toByteArray())
        val sha3 = SHA3.Digest512().digest(base64)

        val originalRequest = chain.request()
            .newBuilder()
            .addHeader("X-Date", time)
            .addHeader("User-Data", Hex.toHexString(sha3))
            .addHeader("Authorization", getToken(App.getContext()!!))
            .build()

        val originalResponse = chain.proceed(originalRequest)

        if (originalResponse.code() == 403) {
            val auth = AuthDatabase.getInstance(App.getContext()!!)!!.getAuthDao().getAuth()

            val JSON = MediaType.parse("application/json; charset=utf-8")
            val body = RequestBody.create(JSON, "{\"id\" : \"${auth.id}\", \"password\" : \"${auth.password}\"}")

            val authRequest = originalRequest.newBuilder()
                .url("https://api.dms.istruly.sexy/account/auth")
                .addHeader("X-Date", time)
                .addHeader("User-Data", Hex.toHexString(sha3))
                .post(body)
                .build()
            val response = chain.proceed(authRequest)

            Log.d("SecretHeaderInterceptor", "Reload jwt response: ${response.code().toString()}")
//            Log.d("되라 제발", "${auth.id}, ${auth.password}")
            if (response.code() == 200) {
                val responseBody = JSONObject(response.body()?.string())
                saveToken(App.getContext()!!, responseBody["accessToken"].toString())
                val newReuqest = chain.request()
                    .newBuilder()
                    .addHeader("X-Date", time)
                    .addHeader("User-Data", Hex.toHexString(sha3))
                    .removeHeader("Authorization")
                    .addHeader("Authorization", getToken(App.getContext()!!))
                    .build()
                return chain.proceed(newReuqest)
            } else {
                removeToken(App.getContext()!!)
                /* AuthDatabase.getInstance(App.getContext()!!)?.getAuthDao()?.insert(Auth("",""))*/
                return originalResponse
            }
        } else {
            return originalResponse
        }

    }
}