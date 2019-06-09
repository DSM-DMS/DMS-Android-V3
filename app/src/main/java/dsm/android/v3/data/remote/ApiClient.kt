package dsm.android.v3.data.remote

import com.google.gson.JsonObject
import dsm.android.v3.domain.entity.*
import dsm.android.v3.presentation.model.NoticeDescriptionModel
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Response

class ApiClient(val api: API) {

    fun signIn(body: Any?): Single<Response<AuthModel>> =
        api.signIn(body)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    fun signUp(body: Any?): Single<Response<Unit>> =
        api.signUp(body)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    fun getMeal(day: String): Single<Response<JsonObject>> =
        api.getMeal(day)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    fun getNoticeList(): Single<Response<NoticeListModel>> =
        api.getNoticeList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    fun getRulesList(): Single<Response<RulesModel>> =
        api.getRulesList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    fun getNoticeDescription(notice_id: String): Single<Response<NoticeDescriptionModel>> =
        api.getNoticeDescription(notice_id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    fun getRulesDescription(rule_id: String): Single<Response<NoticeDescriptionModel>> =
        api.getRulesDescription(rule_id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    fun getPointLog(): Single<Response<PointLogListModel>> =
        api.getPointLog()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    fun changePw(body: Any?): Single<Response<Unit>> =
        api.changePw(body)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    fun getMap(time: Int, classNum: Int): Single<Response<ApplyExtensionStudyModel>> =
        api.getMap(time, classNum)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    fun applyExtension(time: Int, body: HashMap<String, Int>): Single<Response<Unit>> =
        api.applyExtension(time, body)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    fun deleteExtension(time: Int): Single<Response<Unit>> =
        api.deleteExtension(time)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    fun getStayInfo(): Single<Response<ApplyStayingModel>> =
        api.getStayInfo()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    fun applyStay(body: Any?): Single<Response<Unit>> =
        api.applyStay(body)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    fun getGoingOutInfo(): Single<Response<ApplyGoingOutModel>> =
        api.getGoingOutInfo()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    fun applyGoingOutDoc(body: Any?): Single<Response<Unit>> =
        api.applyGoingOutDoc(body)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    fun editGoingOut(body: Any?): Single<Response<Unit>> =
        api.editGoingOut(body)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    fun getMusic(): Single<Response<ApplyMusicModel>> =
        api.getMusic()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    fun applyMusic(body: Any?): Single<Response<Unit>> =
        api.applyMusic(body)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    fun deleteMusic(body: Any?): Single<Response<Unit>> =
        api.deleteMusic(body)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    fun deleteGoingOut(body: Any?): Single<Response<Unit>> =
        api.deleteGoingOut(body)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    fun getBasicInfo(): Single<Response<MyPageInfoModel>> =
        api.getBasicInfo()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    fun reportInstitution(body: Any?): Single<Response<Unit>> =
        api.reportInstitution(body)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    fun reportBug(body: Any?): Single<Response<Unit>> =
        api.reportBug(body)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
}