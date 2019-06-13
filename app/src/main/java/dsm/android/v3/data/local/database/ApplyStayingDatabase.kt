package dsm.android.v3.data.local.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import dsm.android.v3.data.local.dao.ApplyStayingDao
import dsm.android.v3.domain.entity.applyStaying.ApplyStayingModel

@Database(entities = arrayOf(ApplyStayingModel::class), version = 1)
abstract class ApplyStayingDatabase: RoomDatabase() {

    abstract fun getApplyStayingDao(): ApplyStayingDao

    companion object {

        private var INSTANCE: ApplyStayingDatabase? = null

        fun getInstance(context: Context): ApplyStayingDatabase? {

            if(INSTANCE == null) {
                synchronized(ApplyStayingDatabase::class) {
                    INSTANCE = Room.databaseBuilder(
                        context,
                        ApplyStayingDatabase::class.java,
                        "staying.db")
                        .build()
                }
            }
            return INSTANCE
        }
    }
}