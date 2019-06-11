package dsm.android.v3.domain.repository.applyMusic

import dsm.android.v3.data.local.dao.OfflineDao
import dsm.android.v3.data.remote.ApiClient
import dsm.android.v3.domain.entity.applyMusic.ApplyMusicDetailModel
import dsm.android.v3.domain.entity.applyMusic.ApplyMusicModel
import io.reactivex.Flowable
import io.reactivex.Single
import retrofit2.Response

class ApplyMusicRepositoryImpl(
    val apiClient: ApiClient,
    val dao: OfflineDao): ApplyMusicRepository {
    override fun getMusic(): Single<Response<ApplyMusicModel>> = apiClient.getMusic()

    override fun applyMusic(body: Any?): Single<Response<Unit>> = apiClient.applyMusic(body)

    override fun deleteMusic(body: Any?): Single<Response<Unit>> = apiClient.deleteMusic(body)

    override fun loadMusic(): Single<ApplyMusicDetailModel> = dao.getMusic()

    override fun saveMusic(applyMusicDetailModel: Array<ApplyMusicDetailModel>) = dao.insertMusic(applyMusicDetailModel)
}