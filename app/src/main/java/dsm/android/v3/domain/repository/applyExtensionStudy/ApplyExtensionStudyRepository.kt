package dsm.android.v3.domain.repository.applyExtensionStudy

import dsm.android.v3.domain.entity.extensionStudy.ApplyExtensionStudyModel
import io.reactivex.Flowable
import io.reactivex.Single
import retrofit2.Response

interface ApplyExtensionStudyRepository {
    fun getMap(time: Int, classNum: Int): Single<Response<ApplyExtensionStudyModel>>

    fun applyExtension(time: Int, body: HashMap<String, Int>): Single<Response<Unit>>

    fun deleteExtension(time: Int): Single<Response<Unit>>

    fun loadExtensionMap(): Flowable<ApplyExtensionStudyModel>

    fun saveExtensionMap(map: ApplyExtensionStudyModel)
}