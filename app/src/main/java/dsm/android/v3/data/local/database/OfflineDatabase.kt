package dsm.android.v3.data.local.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import dsm.android.v3.data.local.dao.*
import dsm.android.v3.domain.entity.applyGoingOut.ApplyGoingOutModel
import dsm.android.v3.domain.entity.applyMusic.ApplyMusicDetailModel
import dsm.android.v3.domain.entity.applyStaying.ApplyStayingModel
import dsm.android.v3.domain.entity.extensionStudy.ApplyExtensionStudyModel
import dsm.android.v3.domain.entity.meal.MealEntity
import dsm.android.v3.domain.entity.myPageInfo.MyPageInfoModel
import dsm.android.v3.domain.entity.notice.NoticeEntity
import dsm.android.v3.domain.entity.pointLogList.PointLogItemModel
import dsm.android.v3.domain.entity.rules.RulesEntity

@Database(entities = arrayOf(
    ApplyExtensionStudyModel::class,
    ApplyGoingOutModel.ApplyGoingDataModel::class,
    ApplyMusicDetailModel::class,
    ApplyStayingModel::class,
    MealEntity::class,
    MyPageInfoModel::class,
    NoticeEntity::class,
    PointLogItemModel::class,
    RulesEntity::class), version = 1)
abstract class OfflineDatabase: RoomDatabase() {
    abstract fun getOfflineDao(): OfflineDao
}