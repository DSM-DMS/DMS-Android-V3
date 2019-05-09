package dsm.android.v3.data.local.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import dsm.android.v3.domain.entity.Auth
import dsm.android.v3.data.local.dao.AuthDao

@Database(entities = arrayOf(Auth::class), version = 1)
abstract class AuthDatabase: RoomDatabase() {

    abstract fun getAuthDao(): AuthDao

}