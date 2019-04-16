package dsm.android.v3.ui.applyGoingOut.applyGoing

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.ViewModel
import dsm.android.v3.connecter.api
import dsm.android.v3.model.ApplyGoingModel
import dsm.android.v3.ui.applyGoingOut.applyGoingLog.ApplyGoingLogData
import dsm.android.v3.util.LifecycleCallback
import dsm.android.v3.util.SingleLiveEvent
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ApplyGoingViewModel : ViewModel(), LifecycleCallback {

    val setViewPagerSingleLiveEvent = SingleLiveEvent<Any>()
    val intentApplyGoingDocSingleLiveEvent = SingleLiveEvent<Any>()
    val createShortToastSingleLiveEvent = SingleLiveEvent<String>()

    override fun apply(event: Lifecycle.Event) {
        when (event) {
            Lifecycle.Event.ON_START -> {
                api.getGoingOutInfo().enqueue(object : Callback<ApplyGoingModel> {
                    override fun onResponse(call: Call<ApplyGoingModel>, response: Response<ApplyGoingModel>) {
                        when (response.code()) {
                            200 -> setApplyGoingData(response.body()!!)
                            204 -> setShortToast("외출신청 정보가 없습니다.")
                            403 -> setShortToast("외출신청 정보 조회 권한이 없습니다.")
                            500 -> setShortToast("로그인이 필요합니다.")
                            else -> setShortToast("오류코드: ${response.code()}")
                        }
                    }

                    override fun onFailure(call: Call<ApplyGoingModel>, t: Throwable) {
                        setShortToast("오류가 발생했습니다.")
                    }
                })
                setApplyGoingData(
                    ApplyGoingModel(
                        ApplyGoingLogData.saturdayItemList,
                        ApplyGoingLogData.sundayItemList,
                        ApplyGoingLogData.workdayItemList
                    )
                )
            }
        }
    }

    fun setApplyGoingData(applyGoingList: ApplyGoingModel) {
        ApplyGoingLogData.saturdayItemList = applyGoingList.saturdayList
        ApplyGoingLogData.sundayItemList = applyGoingList.sundayList
        ApplyGoingLogData.workdayItemList = applyGoingList.workdayList
        setViewPagerSingleLiveEvent.call()
    }

    fun setShortToast(text: String) {
        if (createShortToastSingleLiveEvent.value != text)
            createShortToastSingleLiveEvent.value = text
    }

    fun applyGoingClickDoc() = intentApplyGoingDocSingleLiveEvent.call()
}
