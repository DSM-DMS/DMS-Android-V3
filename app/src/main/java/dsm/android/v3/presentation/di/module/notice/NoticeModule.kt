package dsm.android.v3.presentation.di.module.notice

import android.arch.persistence.room.Room
import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import dsm.android.v3.data.local.dao.NoticeDao
import dsm.android.v3.data.local.dao.RulesDao
import dsm.android.v3.data.local.database.NoticeDatabase
import dsm.android.v3.data.local.database.RulesDatabase
import dsm.android.v3.data.remote.ApiClient
import dsm.android.v3.domain.repository.notice.NoticeRepository
import dsm.android.v3.domain.repository.notice.NoticeRepositoryImpl
import dsm.android.v3.presentation.di.scope.ActivityScope
import dsm.android.v3.presentation.di.scope.FragmentScope
import dsm.android.v3.ui.fragment.notice.NoticeDescriptionFragment

@Module
abstract class NoticeModule {

    @Module
    companion object {
        @ActivityScope
        @JvmStatic
        @Provides
        fun provideNoticeDataBase(context: Context) : NoticeDatabase
                = Room.databaseBuilder(context, NoticeDatabase::class.java, "notice.db").build()

        @ActivityScope
        @JvmStatic
        @Provides
        fun provideRulesDataBase(context: Context) : RulesDatabase
                = Room.databaseBuilder(context, RulesDatabase::class.java, "rules.db").build()

        @ActivityScope
        @JvmStatic
        @Provides
        fun provideNoticeDao(database: NoticeDatabase): NoticeDao
                = database.getNoticeDao()

        @ActivityScope
        @JvmStatic
        @Provides
        fun provideRulesDao(database: RulesDatabase): RulesDao
                = database.getRulesDao()

        @ActivityScope
        @JvmStatic
        @Provides
        fun provideRepository(apiClient: ApiClient, noticeDao: NoticeDao, rulesDao: RulesDao): NoticeRepository
                = NoticeRepositoryImpl(apiClient, noticeDao, rulesDao)
    }


    @FragmentScope
    @ContributesAndroidInjector()
    abstract fun noticeDescriptionFragment(): NoticeDescriptionFragment
}