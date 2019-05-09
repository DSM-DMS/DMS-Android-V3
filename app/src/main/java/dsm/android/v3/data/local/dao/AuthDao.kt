package dsm.android.v3.data.local.dao

import android.arch.persistence.room.*
import dsm.android.v3.domain.entity.Auth

@Dao
interface AuthDao{
    @Query("SELECT * FROM auth")
    fun getAuth(): Auth

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg auth: Auth)

    @Delete
    fun delete(vararg auth: Auth)
}