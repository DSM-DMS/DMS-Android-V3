package dsm.android.v3.data.local.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import dsm.android.v3.data.local.dao.RulesDao
import dsm.android.v3.domain.entity.notice.NoticeModel
import dsm.android.v3.domain.entity.rules.RulesModel

@Database(entities = arrayOf(NoticeModel::class), version = 1)
abstract class RulesDatabase: RoomDatabase() {

    abstract fun getRulesDao(): RulesDao

    companion object {

        private var INSTANCE: RulesDatabase? = null

        fun getInstance(context: Context): RulesDatabase? {

            if(INSTANCE == null) {
                synchronized(RulesDatabase::class) {
                    INSTANCE = Room.databaseBuilder(
                        context,
                        RulesDatabase::class.java,
                        "extension.db")
                        .build()
                }
            }
            return INSTANCE
        }
    }
}