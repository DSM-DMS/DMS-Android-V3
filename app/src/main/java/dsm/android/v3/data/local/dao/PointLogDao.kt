package dsm.android.v3.data.local.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import dsm.android.v3.domain.entity.PointLogListModel
import io.reactivex.Flowable

@Dao
interface PointLogDao {
    @Query("SELECT * FROM pointloglistmodel")
    fun getAll(): Flowable<PointLogListModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg pointLogListModel: PointLogListModel)
}