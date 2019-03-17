package dsm.android.v3.ui.signIn

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context

@Database(entities = arrayOf(Auth::class), version = 1)
abstract class AuthDatabase: RoomDatabase() {

    abstract fun getAuthDao(): AuthDao

    companion object {

        private var INSTANCE: AuthDatabase? = null

        fun getInstance(context: Context): AuthDatabase? {

            if(INSTANCE == null) {
                synchronized(AuthDatabase::class) {
                    INSTANCE = Room.databaseBuilder(
                        context,
                        AuthDatabase::class.java,
                        "auth.db")
                        .build()
                }
            }
            return INSTANCE
        }

    }

}