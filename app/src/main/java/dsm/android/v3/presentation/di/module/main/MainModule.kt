package dsm.android.v3.presentation.di.module.main

import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import dsm.android.v3.presentation.di.scope.FragmentScope
import dsm.android.v3.ui.fragment.meal.MealFragment
import dsm.android.v3.ui.fragment.mypage.MyPageFragment
import dsm.android.v3.ui.fragment.notice.NoticeFragment
import dsm.android.v3.ui.fragment.putIn.PutInFragment

@Module
abstract class MainModule {

    @FragmentScope
    @ContributesAndroidInjector
    abstract fun mealFragment(): MealFragment

    @FragmentScope
    @ContributesAndroidInjector
    abstract fun myPageFragment(): MyPageFragment

    @FragmentScope
    @ContributesAndroidInjector
    abstract fun noticeFragment(): NoticeFragment

    @FragmentScope
    @ContributesAndroidInjector
    abstract fun putInFragment(): PutInFragment
}