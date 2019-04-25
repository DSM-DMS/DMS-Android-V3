package dsm.android.v3.ui.signIn

import android.arch.persistence.room.*
import dsm.android.v3.database.Auth

@Dao
interface AuthDao{
    @Query("SELECT * FROM auth")
    fun getAuth(): Auth

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg auth: Auth)

    @Delete
    fun delete(vararg auth: Auth)
}