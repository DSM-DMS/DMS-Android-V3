package dsm.android.v3.data.local.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import dsm.android.v3.domain.entity.RulesModel
import io.reactivex.Flowable

@Dao
interface RulesDao {
    @Query("SELECT * FROM rulesmodel")
    fun getAll(): Flowable<RulesModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg rulesModel: RulesModel)
}