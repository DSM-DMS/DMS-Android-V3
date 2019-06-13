package dsm.android.v3.data.local.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import dsm.android.v3.data.local.dao.NoticeDao
import dsm.android.v3.domain.entity.notice.NoticeModel

@Database(entities = arrayOf(NoticeModel::class), version = 1)
abstract class NoticeDatabase: RoomDatabase() {

    abstract fun getNoticeDao(): NoticeDao

    companion object {

        private var INSTANCE: NoticeDatabase? = null

        fun getInstance(context: Context): NoticeDatabase? {

            if(INSTANCE == null) {
                synchronized(NoticeDatabase::class) {
                    INSTANCE = Room.databaseBuilder(
                        context,
                        NoticeDatabase::class.java,
                        "notice.db")
                        .build()
                }
            }
            return INSTANCE
        }
    }
}