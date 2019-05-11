package dsm.android.v3.domain.repository.applyExtensionStudy

import dsm.android.v3.data.remote.ApiClient
import dsm.android.v3.domain.entity.ExtensionModel
import io.reactivex.Single
import retrofit2.Response

class ApplyExtensionStudyRepositoryImpl(val apiClient: ApiClient): ApplyExtensionStudyRepository {
    override fun getMap(time: Int, classNum: Int): Single<Response<ExtensionModel>> = apiClient.getMap(time, classNum)

    override fun applyExtension(time: Int, body: HashMap<String, Int>): Single<Response<Unit>> = apiClient.applyExtension(time, body)

    override fun deleteExtension(time: Int): Single<Response<Unit>> = apiClient.deleteExtension(time)

}