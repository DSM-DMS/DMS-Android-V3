package dsm.android.v3.presentation.di.module.main

import dagger.Module
import dagger.android.ContributesAndroidInjector
import dsm.android.v3.presentation.di.module.main.bugReport.BugReportModule
import dsm.android.v3.presentation.di.module.main.institutionReport.InstitutionReportModule
import dsm.android.v3.presentation.di.module.main.logoutModule.LogoutModule
import dsm.android.v3.presentation.di.module.main.meal.MealModule
import dsm.android.v3.presentation.di.module.main.mypage.MyPageModule
import dsm.android.v3.presentation.di.module.main.putIn.PutInModule
import dsm.android.v3.presentation.di.scope.DialogFragmentScope
import dsm.android.v3.presentation.di.scope.FragmentScope
import dsm.android.v3.ui.dialogFragment.bugReportDialog.BugReportDialogFragment
import dsm.android.v3.ui.dialogFragment.institutionReportDialog.InstitutionDialogFragment
import dsm.android.v3.ui.dialogFragment.logOutDialog.LogoutDialogFragment
import dsm.android.v3.ui.fragment.meal.MealFragment
import dsm.android.v3.ui.fragment.mypage.MyPageFragment
import dsm.android.v3.ui.fragment.putIn.PutInFragment

@Module
abstract class MainModule {

    @FragmentScope
    @ContributesAndroidInjector(modules = [MealModule::class])
    abstract fun mealFragment(): MealFragment

    @FragmentScope
    @ContributesAndroidInjector(modules = [MyPageModule::class])
    abstract fun myPageFragment(): MyPageFragment

    @FragmentScope
    @ContributesAndroidInjector(modules = [PutInModule::class])
    abstract fun putInFragment(): PutInFragment

    @DialogFragmentScope
    @ContributesAndroidInjector(modules = [BugReportModule::class])
    abstract fun bugReportDialogFragment(): BugReportDialogFragment

    @DialogFragmentScope
    @ContributesAndroidInjector(modules = [LogoutModule::class])
    abstract fun logoutDialogFragment(): LogoutDialogFragment

    @DialogFragmentScope
    @ContributesAndroidInjector(modules = [InstitutionReportModule::class])
    abstract fun institutionDialogFragment(): InstitutionDialogFragment

}