package dsm.android.v3.data.local.dao

import android.arch.persistence.room.*
import dsm.android.v3.domain.entity.ApplyExtensionStudyModel
import io.reactivex.Flowable

@Dao
interface ApplyExtensionStudyDao {
    @Query("SELECT * FROM applyextensionstudymodel")
    fun getAll(): Flowable<ApplyExtensionStudyModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg applyExtensionStudyModels: ApplyExtensionStudyModel)
}