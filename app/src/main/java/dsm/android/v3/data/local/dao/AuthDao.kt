package dsm.android.v3.ui.activity.signIn

import android.arch.persistence.room.*
import dsm.android.v3.data.entity.Auth

@Dao
interface AuthDao{
    @Query("SELECT * FROM auth")
    fun getAuth(): Auth

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg auth: Auth)

    @Delete
    fun delete(vararg auth: Auth)
}