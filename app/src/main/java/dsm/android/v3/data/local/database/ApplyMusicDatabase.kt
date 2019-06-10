package dsm.android.v3.data.local.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import dsm.android.v3.data.local.dao.ApplyMusicDao
import dsm.android.v3.domain.entity.applyMusic.ApplyMusicDetailModel
import dsm.android.v3.domain.entity.applyMusic.ApplyMusicModel

@Database(entities = arrayOf(ApplyMusicDetailModel::class), version = 1)
abstract class ApplyMusicDatabase: RoomDatabase() {

    abstract fun getApplyMusicDao(): ApplyMusicDao

    companion object {

        private var INSTANCE: ApplyMusicDatabase? = null

        fun getInstance(context: Context): ApplyMusicDatabase? {

            if(INSTANCE == null) {
                synchronized(ApplyMusicDatabase::class) {
                    INSTANCE = Room.databaseBuilder(
                        context,
                        ApplyMusicDatabase::class.java,
                        "music.db")
                        .build()
                }
            }
            return INSTANCE
        }
    }
}