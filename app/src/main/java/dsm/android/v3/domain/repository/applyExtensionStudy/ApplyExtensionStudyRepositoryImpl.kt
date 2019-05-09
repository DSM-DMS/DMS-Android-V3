package dsm.android.v3.domain.repository.applyExtensionStudy

import dsm.android.v3.data.remote.ApiClient
import dsm.android.v3.domain.entity.ExtensionModel
import io.reactivex.Single

class ApplyExtensionStudyRepositoryImpl(val apiClient: ApiClient): ApplyExtensionStudyRepository {
    override fun getMap(time: Int, classNum: Int): Single<ExtensionModel> = apiClient.getMap(time, classNum)

    override fun applyExtension(time: Int, body: HashMap<String, Int>): Single<Unit> = apiClient.applyExtension(time, body)

    override fun deleteExtension(time: Int): Single<Unit> = apiClient.deleteExtension(time)

}