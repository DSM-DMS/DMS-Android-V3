package dsm.android.v3.data.local.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import dsm.android.v3.data.local.dao.MyPageInfoDao
import dsm.android.v3.domain.entity.myPageInfo.MyPageInfoModel

@Database(entities = arrayOf(MyPageInfoModel::class), version = 1)
abstract class MyPageInfoDatabase: RoomDatabase() {

    abstract fun getMyPageInfoDao(): MyPageInfoDao

    companion object {

        private var INSTANCE: MyPageInfoDatabase? = null

        fun getInstance(context: Context): MyPageInfoDatabase? {

            if(INSTANCE == null) {
                synchronized(MyPageInfoDatabase::class) {
                    INSTANCE = Room.databaseBuilder(
                        context,
                        MyPageInfoDatabase::class.java,
                        "myPageInfo.db")
                        .build()
                }
            }
            return INSTANCE
        }

    }
}