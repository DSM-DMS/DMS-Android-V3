package dsm.android.v3.data.local.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import dsm.android.v3.data.local.dao.ApplyGoingOutDao
import dsm.android.v3.domain.entity.applyGoingOut.ApplyGoingOutModel

@Database(entities = arrayOf(ApplyGoingOutModel.ApplyGoingDataModel::class), version = 1)
abstract class ApplyGoingOutDatabase: RoomDatabase() {

    abstract fun getApplyGoingOutDao(): ApplyGoingOutDao

    companion object {

        private var INSTANCE: ApplyGoingOutDatabase? = null

        fun getInstance(context: Context): ApplyGoingOutDatabase? {

            if(INSTANCE == null) {
                synchronized(ApplyGoingOutDatabase::class) {
                    INSTANCE = Room.databaseBuilder(
                        context,
                        ApplyGoingOutDatabase::class.java,
                        "goingOut.db")
                        .build()
                }
            }
            return INSTANCE
        }
    }
}