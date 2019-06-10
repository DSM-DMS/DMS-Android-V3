package dsm.android.v3.data.local.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import dsm.android.v3.domain.entity.applyStaying.ApplyStayingModel
import io.reactivex.Flowable

@Dao
interface ApplyStayingDao {
    @Query("SELECT * FROM applystayingmodel")
    fun getAll(): Flowable<ApplyStayingModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg applyStayingModel: ApplyStayingModel)
}