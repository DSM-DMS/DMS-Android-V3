package dsm.android.v3.presentation.di.module.main.mypage

import dagger.Module
import dagger.Provides
import dsm.android.v3.data.remote.ApiClient
import dsm.android.v3.domain.repository.mypage.MyPageRepository
import dsm.android.v3.domain.repository.mypage.MyPageRepositoryImpl
import dsm.android.v3.presentation.di.scope.FragmentScope
import dsm.android.v3.presentation.viewModel.mypage.MyPageViewModelFactory

@Module
class MyPageModule {
    @FragmentScope
    @Provides
    fun provideRepository(apiClient: ApiClient): MyPageRepository
            = MyPageRepositoryImpl(apiClient)

    @FragmentScope
    @Provides
    fun provideViewModelFactory(repository: MyPageRepository): MyPageViewModelFactory
            = MyPageViewModelFactory(repository)
}