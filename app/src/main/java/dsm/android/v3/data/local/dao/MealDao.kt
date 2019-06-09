package dsm.android.v3.data.local.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import dsm.android.v3.domain.entity.MealModel
import io.reactivex.Flowable

@Dao
interface MealDao {
    @Query("SELECT * FROM mealmodel")
    fun getAll(): Flowable<MealModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg mealModel: MealModel)
}