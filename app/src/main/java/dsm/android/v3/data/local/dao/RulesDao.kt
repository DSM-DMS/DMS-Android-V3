package dsm.android.v3.data.local.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import dsm.android.v3.domain.entity.notice.NoticeModel
import dsm.android.v3.domain.entity.rules.RulesModel
import io.reactivex.Flowable

@Dao
interface RulesDao {
    @Query("SELECT * FROM noticemodel")
    fun getAll(): Flowable<NoticeModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg rulesModel: NoticeModel)
}