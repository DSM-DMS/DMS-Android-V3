package dsm.android.v3.domain.repository.applyMusic

import dsm.android.v3.domain.entity.applyMusic.ApplyMusicDetailModel
import dsm.android.v3.domain.entity.applyMusic.ApplyMusicModel
import io.reactivex.Flowable
import io.reactivex.Single
import retrofit2.Response

interface ApplyMusicRepository {
    fun getMusic(): Single<Response<ApplyMusicModel>>

    fun applyMusic(body: Any?): Single<Response<Unit>>

    fun deleteMusic(body: Any?): Single<Response<Unit>>

    fun loadMusic(): Single<ApplyMusicDetailModel>

    fun saveMusic(applyMusicDetailModel: Array<ApplyMusicDetailModel>)
}