package dsm.android.v3.domain.repository.applyExtensionStudy

import dsm.android.v3.domain.entity.ExtensionModel
import io.reactivex.Single
import retrofit2.Response

interface ApplyExtensionStudyRepository {
    fun getMap(time: Int, classNum: Int): Single<Response<ExtensionModel>>

    fun applyExtension(time: Int, body: HashMap<String, Int>): Single<Response<Unit>>

    fun deleteExtension(time: Int): Single<Response<Unit>>
}