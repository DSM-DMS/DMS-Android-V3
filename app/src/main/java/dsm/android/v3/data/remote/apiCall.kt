package dsm.android.v3.data.remote

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

val api = Connecter.createApi()

data class ConnectModel<T>(var onSuccess: (Response<T>.() -> Unit)? = null, var onFailure: (() -> Unit)? = null)

fun <T> connectWIthModel(connect: Call<T>, model: ConnectModel<T>.() -> Unit) {
    val connectModel = ConnectModel<T>()
    connectModel.model()
    connect.enqueue(object : Callback<T> {
        override fun onResponse(call: Call<T>?, response: Response<T>?) {
            connectModel.onSuccess?.let { response?.it() }
        }

        override fun onFailure(call: Call<T>?, t: Throwable?) {
            connectModel.onFailure?.let { it() }
        }

    })
}
