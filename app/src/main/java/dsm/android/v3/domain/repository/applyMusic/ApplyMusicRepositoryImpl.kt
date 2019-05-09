package dsm.android.v3.domain.repository.applyMusic

import dsm.android.v3.data.remote.ApiClient
import dsm.android.v3.domain.entity.ApplyMusicModel
import io.reactivex.Single

class ApplyMusicRepositoryImpl(val apiClient: ApiClient): ApplyMusicRepository {
    override fun getMusic(): Single<ApplyMusicModel> = apiClient.getMusic()

    override fun applyMusic(body: Any?): Single<Unit> = apiClient.applyMusic(body)

    override fun deleteMusic(body: Any?): Single<Void> = apiClient.deleteMusic(body)
}