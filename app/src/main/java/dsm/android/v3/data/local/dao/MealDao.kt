package dsm.android.v3.data.local.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import dsm.android.v3.domain.entity.meal.MealEntity
import dsm.android.v3.domain.entity.meal.MealModel
import io.reactivex.Flowable

@Dao
interface MealDao {
    @Query("SELECT * FROM mealentity")
    fun getAll(): Flowable<MealEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg mealEntity: MealEntity)
}