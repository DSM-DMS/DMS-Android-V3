package dsm.android.v3.ui.signIn

import android.arch.persistence.room.*

@Dao
interface AuthDao{
    @Query("SELECT * FROM auth")
    fun getAuth(): Auth

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg person: Auth)
}