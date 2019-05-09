package dsm.android.v3.domain.repository.applyMusic

import dsm.android.v3.domain.entity.ApplyMusicModel
import io.reactivex.Single

interface ApplyMusicRepository {
    fun getMusic(): Single<ApplyMusicModel>

    fun applyMusic(body: Any?): Single<Unit>

    fun deleteMusic(body: Any?): Single<Void>
}