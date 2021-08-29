package dsm.android.v3.presentation.di.module

import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dsm.android.v3.data.remote.API
import dsm.android.v3.data.remote.ApiClient
import dsm.android.v3.data.remote.EaesAPI
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
class ApiModule {

    companion object {
        private val eaesBaseUrl = "https://eaes03njn2.execute-api.ap-northeast-2.amazonaws.com"
    }

    @Provides
    @Singleton
    fun provideApi(@Named("retrofit") retrofit: Retrofit): API = retrofit.create(API::class.java)

    @Provides
    @Singleton
    fun provideApiClient(Api: API): ApiClient = ApiClient(Api)

    @Provides
    @Singleton
    fun provideEaseApi(gson: Gson, okHttpClient: OkHttpClient): EaesAPI = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create(gson))
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .baseUrl(eaesBaseUrl)
        .client(okHttpClient)
        .build().create(EaesAPI::class.java)

}