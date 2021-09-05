package dsm.android.v3.presentation.di.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import dsm.android.v3.presentation.di.module.applyExtensionStudy.ApplyExtensionStudyModule
import dsm.android.v3.presentation.di.module.applyGoingOut.ApplyGoingOutModule
import dsm.android.v3.presentation.di.module.applyMeal.ApplyMealModule
import dsm.android.v3.presentation.di.module.applyMusic.ApplyMusicDomModule
import dsm.android.v3.presentation.di.module.applyStaying.ApplyStayingModule
import dsm.android.v3.presentation.di.module.changePassword.ChangePasswordModule
import dsm.android.v3.presentation.di.module.main.MainModule
import dsm.android.v3.presentation.di.module.notice.NoticeModule
import dsm.android.v3.presentation.di.module.pointLog.PointLogModule
import dsm.android.v3.presentation.di.module.register.RegisterModule
import dsm.android.v3.presentation.di.module.setting.SettingModule
import dsm.android.v3.presentation.di.module.signIn.SignInModule
import dsm.android.v3.presentation.di.module.splash.SplashModule
import dsm.android.v3.presentation.di.scope.ActivityScope
import dsm.android.v3.ui.activity.applyExtensionStudy.ApplyExtensionStudyActivity
import dsm.android.v3.ui.activity.applyGoingOut.ApplyGoingOutActivity
import dsm.android.v3.ui.activity.applyMeal.ApplyMealActivity
import dsm.android.v3.ui.activity.applyMusic.ApplyMusicDomActivity
import dsm.android.v3.ui.activity.applyStaying.ApplyStayingActivity
import dsm.android.v3.ui.activity.changePassword.ChangePasswordActivity
import dsm.android.v3.ui.activity.main.MainActivity
import dsm.android.v3.ui.activity.notice.NoticeActivity
import dsm.android.v3.ui.activity.pointLog.PointLogActivity
import dsm.android.v3.ui.activity.register.RegisterActivity
import dsm.android.v3.ui.activity.setting.SettingActivity
import dsm.android.v3.ui.activity.signIn.SignInActivity
import dsm.android.v3.ui.activity.splash.SplashActivity

@Module
abstract class ActivityModule {

    @ActivityScope
    @ContributesAndroidInjector(modules = [ApplyExtensionStudyModule::class])
    abstract fun applyExtensionStudyActivity(): ApplyExtensionStudyActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = [ApplyMusicDomModule::class])
    abstract fun applyMusicDomActivity(): ApplyMusicDomActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = [ApplyMealModule::class])
    abstract fun applyMealActivity(): ApplyMealActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = [ApplyGoingOutModule::class])
    abstract fun applyGoingOutActivity(): ApplyGoingOutActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = [MainModule::class])
    abstract fun mainActivity(): MainActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = [ApplyStayingModule::class])
    abstract fun applyStayingActivity(): ApplyStayingActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = [ChangePasswordModule::class])
    abstract fun changePasswordActivity(): ChangePasswordActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = [NoticeModule::class])
    abstract fun noticeActivity(): NoticeActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = [PointLogModule::class])
    abstract fun pointLogActivity(): PointLogActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = [RegisterModule::class])
    abstract fun registerActivity(): RegisterActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = [SignInModule::class])
    abstract fun signInActivity(): SignInActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = [SplashModule::class])
    abstract fun splashActivity(): SplashActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = [SettingModule::class])
    abstract fun settingActivity(): SettingActivity
}