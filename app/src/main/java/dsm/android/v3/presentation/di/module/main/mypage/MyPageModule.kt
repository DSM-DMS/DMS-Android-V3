package dsm.android.v3.presentation.di.module.main.mypage

import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import dsm.android.v3.data.local.dao.AuthDao
import dsm.android.v3.data.local.shared.LocalStorage
import dsm.android.v3.data.remote.ApiClient
import dsm.android.v3.domain.repository.mypage.MyPageRepository
import dsm.android.v3.domain.repository.mypage.MyPageRepositoryImpl
import dsm.android.v3.presentation.di.scope.DialogFragmentScope
import dsm.android.v3.presentation.di.scope.FragmentScope
import dsm.android.v3.presentation.viewModel.mypage.MyPageViewModelFactory
import dsm.android.v3.ui.dialogFragment.bugReportDialog.BugReportDialogFragment
import dsm.android.v3.ui.dialogFragment.institutionReportDialog.InstitutionDialogFragment
import dsm.android.v3.ui.dialogFragment.logOutDialog.LogoutDialogFragment

@Module
abstract class MyPageModule {
    @FragmentScope
    @Provides
    fun provideRepository(apiClient: ApiClient, authDao: AuthDao, localStorage: LocalStorage): MyPageRepository
            = MyPageRepositoryImpl(apiClient, localStorage, authDao)

    @FragmentScope
    @Provides
    fun provideViewModelFactory(repository: MyPageRepository): MyPageViewModelFactory
            = MyPageViewModelFactory(repository)

    @DialogFragmentScope
    @ContributesAndroidInjector
    abstract fun bugReportDialogFragment(): BugReportDialogFragment

    @DialogFragmentScope
    @ContributesAndroidInjector
    abstract fun logoutDialogFragment(): LogoutDialogFragment

    @DialogFragmentScope
    @ContributesAndroidInjector
    abstract fun institutionDialogFragment(): InstitutionDialogFragment
}