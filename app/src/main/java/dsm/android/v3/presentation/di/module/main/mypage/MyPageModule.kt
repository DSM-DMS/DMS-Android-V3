package dsm.android.v3.presentation.di.module.main.mypage

import android.arch.persistence.room.Room
import android.content.Context
import dagger.Module
import dagger.Provides
import dsm.android.v3.data.local.dao.MyPageInfoDao
import dsm.android.v3.data.local.database.MyPageInfoDatabase
import dsm.android.v3.data.remote.ApiClient
import dsm.android.v3.domain.repository.mypage.MyPageRepository
import dsm.android.v3.domain.repository.mypage.MyPageRepositoryImpl
import dsm.android.v3.presentation.di.scope.FragmentScope
import dsm.android.v3.presentation.viewModel.mypage.MyPageViewModelFactory

@Module
class MyPageModule {

    @FragmentScope
    @Provides
    fun provideDataBase(context: Context) : MyPageInfoDatabase
            = Room.databaseBuilder(context, MyPageInfoDatabase::class.java, "myPageInfo.db").build()

    @FragmentScope
    @Provides
    fun provideDao(database: MyPageInfoDatabase): MyPageInfoDao
            = database.getMyPageInfoDao()

    @FragmentScope
    @Provides
    fun provideRepository(apiClient: ApiClient, dao: MyPageInfoDao): MyPageRepository
            = MyPageRepositoryImpl(apiClient, dao)

    @FragmentScope
    @Provides
    fun provideViewModelFactory(repository: MyPageRepository): MyPageViewModelFactory
            = MyPageViewModelFactory(repository)
}