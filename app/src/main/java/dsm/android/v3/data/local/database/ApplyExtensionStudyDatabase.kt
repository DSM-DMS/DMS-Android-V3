package dsm.android.v3.data.local.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import dsm.android.v3.data.local.dao.ApplyExtensionStudyDao
import dsm.android.v3.domain.entity.extensionStudy.ApplyExtensionStudyModel

@Database(entities = arrayOf(ApplyExtensionStudyModel::class), version = 1)
abstract class ApplyExtensionStudyDatabase: RoomDatabase() {

    abstract fun getApplyExtensionStudyDao(): ApplyExtensionStudyDao

    companion object {

        private var INSTANCE: ApplyExtensionStudyDatabase? = null

        fun getInstance(context: Context): ApplyExtensionStudyDatabase? {

            if(INSTANCE == null) {
                synchronized(ApplyExtensionStudyDatabase::class) {
                    INSTANCE = Room.databaseBuilder(
                        context,
                        ApplyExtensionStudyDatabase::class.java,
                        "extension.db")
                        .build()
                }
            }
            return INSTANCE
        }

    }
}