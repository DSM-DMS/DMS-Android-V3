package dsm.android.v3.data.local.dao

import android.arch.persistence.room.*
import dsm.android.v3.domain.entity.applyGoingOut.ApplyGoingOutModel
import dsm.android.v3.domain.entity.applyMusic.ApplyMusicDetailModel
import dsm.android.v3.domain.entity.applyStaying.ApplyStayingModel
import dsm.android.v3.domain.entity.extensionStudy.ApplyExtensionStudyModel
import dsm.android.v3.domain.entity.meal.MealEntity
import dsm.android.v3.domain.entity.myPageInfo.MyPageInfoModel
import dsm.android.v3.domain.entity.notice.NoticeEntity
import dsm.android.v3.domain.entity.pointLogList.PointLogItemModel
import dsm.android.v3.domain.entity.rules.RulesEntity
import io.reactivex.Single
import org.bouncycastle.jcajce.provider.asymmetric.dstu.SignatureSpiLe

@Dao
interface OfflineDao {
    @Query("SELECT * FROM applyextensionstudymodel")
    fun getExtensionStudy(): Single<ArrayList<ApplyExtensionStudyModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertExtensionStudy(vararg applyExtensionStudyModels: ApplyExtensionStudyModel)

    @Query("SELECT * FROM ApplyGoingDataModel")
    fun getGoingOut(): Single<ArrayList<ApplyGoingOutModel.ApplyGoingDataModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertGoingOut(vararg apply: ApplyGoingOutModel.ApplyGoingDataModel)

    @Query("SELECT * FROM applymusicdetailmodel")
    fun getMusic(): Single<ArrayList<ApplyMusicDetailModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMusic(vararg applyMusicModel: ApplyMusicDetailModel)

    @Query("SELECT * FROM applystayingmodel")
    fun getStaying(): Single<ApplyStayingModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertStaying(vararg applyStayingModel: ApplyStayingModel)

    @Query("SELECT * FROM mealentity")
    fun getMeal(): Single<MealEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMeal(vararg mealEntity: MealEntity)

    @Query("SELECT * FROM mypageinfomodel")
    fun getMyPage(): Single<MyPageInfoModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMyPage(vararg myPageInfoModel: MyPageInfoModel)

    @Query("SELECT * FROM noticeentity")
    fun getNotice(): Single<ArrayList<NoticeEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNotice(vararg noticeListModel: NoticeEntity)

    @Query("SELECT * FROM pointlogitemmodel")
    fun getPointLog(): Single<ArrayList<PointLogItemModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPointLog(vararg pointLogItemModel: Array<PointLogItemModel>)

    @Query("SELECT * FROM rulesentity")
    fun getRules(): Single<ArrayList<RulesEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRules(vararg rulesEntity: RulesEntity)
}