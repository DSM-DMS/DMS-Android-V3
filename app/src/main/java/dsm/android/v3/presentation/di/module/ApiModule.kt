package dsm.android.v3.presentation.di.module

import dagger.Module
import dagger.Provides
import dsm.android.v3.data.remote.API
import dsm.android.v3.data.remote.ApiClient
import retrofit2.Retrofit
import javax.inject.Named
import javax.inject.Singleton

@Module
class ApiModule{

    @Provides
    @Singleton
    fun provideApi(@Named("retrofit") retrofit: Retrofit) : API = retrofit.create(API::class.java)

    @Provides
    @Singleton
    fun provideApiClient(Api: API) : ApiClient = ApiClient(Api)

}