package dsm.android.v3.data.local.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import dsm.android.v3.domain.entity.ApplyGoingOutModel
import io.reactivex.Flowable

@Dao
interface ApplyGoingOutDao {
    @Query("SELECT * FROM applygoingoutmodel")
    fun getAll(): Flowable<ApplyGoingOutModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg applyExtensionStudyModels: ApplyGoingOutModel)
}