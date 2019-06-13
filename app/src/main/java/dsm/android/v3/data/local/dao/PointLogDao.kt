package dsm.android.v3.data.local.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import dsm.android.v3.domain.entity.pointLogList.PointLogItemModel
import dsm.android.v3.domain.entity.pointLogList.PointLogListModel
import io.reactivex.Flowable

@Dao
interface PointLogDao {
    @Query("SELECT * FROM pointlogitemmodel")
    fun getAll(): Flowable<PointLogItemModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg pointLogItemModel: PointLogItemModel)
}