package dsm.android.v3.domain.repository.applyExtensionStudy

import dsm.android.v3.domain.entity.ExtensionModel
import io.reactivex.Single

interface ApplyExtensionStudyRepository {
    fun getMap(time: Int, classNum: Int): Single<ExtensionModel>

    fun applyExtension(time: Int, body: HashMap<String, Int>): Single<Unit>

    fun deleteExtension(time: Int): Single<Unit>
}