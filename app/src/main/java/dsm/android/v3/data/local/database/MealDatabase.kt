package dsm.android.v3.data.local.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import dsm.android.v3.data.local.dao.MealDao
import dsm.android.v3.domain.entity.MealModel

@Database(entities = arrayOf(MealModel::class), version = 1)
abstract class MealDatabase: RoomDatabase() {

    abstract fun getMealDao(): MealDao

    companion object {

        private var INSTANCE: MealDatabase? = null

        fun getInstance(context: Context): MealDatabase? {

            if(INSTANCE == null) {
                synchronized(MealDatabase::class) {
                    INSTANCE = Room.databaseBuilder(
                        context,
                        MealDatabase::class.java,
                        "meal.db")
                        .build()
                }
            }
            return INSTANCE
        }

    }
}