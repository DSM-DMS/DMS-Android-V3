package dsm.android.v3.connecter

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Connecter {
    lateinit var retrofit: Retrofit
    lateinit var api: API

    init {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder().addInterceptor(interceptor)
            .addInterceptor(SecretHeaderInterceptor())
            .build()

        retrofit = Retrofit
            .Builder()
            .baseUrl("https://dms-api.istruly.sexy/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()

        api = retrofit.create(API::class.java)
    }

    fun createApi() = retrofit.create(API::class.java)

}