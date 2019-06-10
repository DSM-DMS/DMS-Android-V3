package dsm.android.v3.data.local.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import dsm.android.v3.domain.entity.applyMusic.ApplyMusicDetailModel
import dsm.android.v3.domain.entity.applyMusic.ApplyMusicModel
import io.reactivex.Flowable

@Dao
interface ApplyMusicDao {
    @Query("SELECT * FROM applymusicdetailmodel")
    fun getAll(): Flowable<ApplyMusicDetailModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg applyMusicModel: ApplyMusicDetailModel)
}