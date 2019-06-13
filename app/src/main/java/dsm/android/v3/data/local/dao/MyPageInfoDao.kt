package dsm.android.v3.data.local.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import dsm.android.v3.domain.entity.myPageInfo.MyPageInfoModel
import io.reactivex.Flowable

@Dao
interface MyPageInfoDao {
    @Query("SELECT * FROM mypageinfomodel")
    fun getAll(): Flowable<MyPageInfoModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg myPageInfoModel: MyPageInfoModel)
}