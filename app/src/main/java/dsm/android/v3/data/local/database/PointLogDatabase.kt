package dsm.android.v3.data.local.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import dsm.android.v3.data.local.dao.PointLogDao
import dsm.android.v3.domain.entity.pointLogList.PointLogItemModel

@Database(entities = arrayOf(PointLogItemModel::class), version = 1)
abstract class PointLogDatabase: RoomDatabase() {

    abstract fun getPointLogDao(): PointLogDao

    companion object {

        private var INSTANCE: PointLogDatabase? = null

        fun getInstance(context: Context): PointLogDatabase? {

            if(INSTANCE == null) {
                synchronized(PointLogDatabase::class) {
                    INSTANCE = Room.databaseBuilder(
                        context,
                        PointLogDatabase::class.java,
                        "pointLog.db")
                        .build()
                }
            }
            return INSTANCE
        }
    }
}