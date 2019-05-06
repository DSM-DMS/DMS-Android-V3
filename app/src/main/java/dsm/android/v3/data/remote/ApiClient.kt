package dsm.android.v3.data.remote

import com.google.gson.JsonObject
import dsm.android.v3.data.entity.*
import dsm.android.v3.presentation.model.NoticeDescriptionModel
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ApiClient(val api: API) {

    fun signIn(body: JsonObject): Single<AuthModel> =
        api.signIn(body)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    fun signUp(body: JsonObject): Single<Unit> =
        api.signUp(body)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    fun getMeal(day: String): Single<JsonObject> =
        api.getMeal(day)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    fun getNoticeList(): Single<NoticeListModel> =
        api.getNoticeList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    fun getRulesList(): Single<RulesModel> =
        api.getRulesList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    fun getNoticeDescription(notice_id: String): Single<NoticeDescriptionModel> =
        api.getNoticeDescription(notice_id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    fun getRulesDescription(rule_id: String): Single<NoticeDescriptionModel> =
        api.getRulesDescription(rule_id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    fun getPointLog(): Single<PointLogResponseModel> =
        api.getPointLog()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    fun changePw(body: JsonObject): Single<Void> =
        api.changePw(body)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    fun getMap(time: Int, classNum: Int): Single<ExtensionModel> =
        api.getMap(time, classNum)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    fun applyExtension(time: Int, body: HashMap<String, Int>): Single<Unit> =
        api.applyExtension(time, body)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    fun deleteExtension(time: Int): Single<Unit> =
        api.deleteExtension(time)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    fun getStayInfo(): Single<ApplyStayingModel> =
        api.getStayInfo()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    fun applyStay(body: Any?): Single<Unit> =
        api.applyStay(body)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    fun getGoingOutInfo(): Single<ApplyGoingOutModel> =
        api.getGoingOutInfo()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    fun applyGoingOutDoc(body: Any?): Single<Unit> =
        api.applyGoingOutDoc(body)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    fun editGoingOut(body: Any?): Single<Unit> =
        api.editGoingOut(body)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    fun getMusic(): Single<ApplyMusicModel> =
        api.getMusic()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    fun applyMusic(body: Any?): Single<Unit> =
        api.applyMusic(body)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    fun deleteMusic(body: Any?): Single<Void> =
        api.deleteMusic(body)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    fun deleteGoingOut(body: Any?): Single<Unit> =
        api.deleteGoingOut(body)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    fun getBasicInfo(): Single<MyPageInfoModel> =
        api.getBasicInfo()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    fun reportInstitution(body: Any?): Single<Unit> =
        api.reportInstitution(body)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    fun reportBug(body: Any?): Single<Unit> =
        api.reportBug(body)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
}