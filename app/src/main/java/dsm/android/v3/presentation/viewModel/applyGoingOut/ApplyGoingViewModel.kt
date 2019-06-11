package dsm.android.v3.presentation.viewModel.applyGoingOut

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.MutableLiveData
import android.view.View
import dsm.android.v3.domain.entity.applyGoingOut.ApplyGoingOutModel
import dsm.android.v3.domain.repository.applyGoingOut.ApplyGoingOutRepository
import dsm.android.v3.presentation.model.ApplyPagerModel
import dsm.android.v3.presentation.model.ApplyGoingLogData
import dsm.android.v3.util.BaseViewModel
import dsm.android.v3.util.LifecycleCallback
import dsm.android.v3.util.SingleLiveEvent

class ApplyGoingViewModel(val applyGoingOutRepository: ApplyGoingOutRepository) : BaseViewModel(), LifecycleCallback {

    val applyGoingDocSingleLiveEvent = SingleLiveEvent<Any>()
    val createShortToastSingleLiveEvent = SingleLiveEvent<String>()
    val applyGoingLogSingleLiveEvent = SingleLiveEvent<View>()

    val applyPagerModelLiveData = MutableLiveData<ArrayList<ApplyPagerModel>>()

    override fun apply(event: Lifecycle.Event) {
        when (event) {
            Lifecycle.Event.ON_START -> {
                add(applyGoingOutRepository.getGoingOutInfo()
                    .subscribe({ response ->
                        when (response.code()) {
                            200 -> {
                                for (model in response.body()!!.saturdayList){
                                    model.week = 0
                                    applyGoingOutRepository.saveGoingOut(model)
                                }
                                for (model in response.body()!!.sundayList) {
                                    model.week = 1
                                    applyGoingOutRepository.saveGoingOut(model)
                                }
                                for (model in response.body()!!.workdayList) {
                                    model.week = 2
                                    applyGoingOutRepository.saveGoingOut(model)
                                }
                                setApplyGoingData(response.body()!!)
                            }
                            204 -> setShortToast("외출신청 정보가 없습니다.")
                            403 -> setShortToast("외출신청 정보 조회 권한이 없습니다.")
                            500 -> setShortToast("로그인이 필요합니다.")
                            else -> setShortToast("오류코드: ${response.code()}")
                        }
                    }, {
                        add(
                            applyGoingOutRepository
                                .loadGoingOut().map {
                                    it.forEach {
                                        if (it.week == 0) ApplyGoingLogData.saturdayItemList.add(it)
                                        else if (it.week == 1) ApplyGoingLogData.sundayItemList.add(it)
                                        else ApplyGoingLogData.workdayItemList.add(it)
                                    }
                                }.subscribe()
                        )
                        setShortToast("네트워크 상태를 확인해주세요.")
                    }))
                setApplyGoingData(
                    ApplyGoingOutModel(
                        ApplyGoingLogData.saturdayItemList,
                        ApplyGoingLogData.sundayItemList,
                        ApplyGoingLogData.workdayItemList
                    )
                )
            }
        }
    }

    fun setApplyGoingData(applyGoingModel: ApplyGoingOutModel) {

        applyPagerModelLiveData.value = arrayListOf(
            ApplyPagerModel(
                "토요외출",
                "토요일 12시 30분부터 점심식사를 한 후 외출이 가능합니다. " +
                        "주말 급식을 신청하지 않은 학생은 바로 외출이 가능합니다. " +
                        "외출한 학생들은 예외 상황이 아닌 이상 17시 30분까지 귀사하여 점호 후 저녁 식사를 해야 합니다.",
                applyGoingModel.saturdayList.size
            ),
            ApplyPagerModel(
                "일요외출",
                "일요일 12시 30분부터 점심식사를 한 후 외출이 가능합니다. " +
                        "주말 급식을 신청하지 않은 학생은 바로 외출이 가능합니다. " +
                        "외출한 학생들은 예외 상황이 아닌 이상 17시 30분까지 귀사하여 점호 후 저녁 식사를 해야 합니다.",
                applyGoingModel.sundayList.size
            ),
            ApplyPagerModel(
                "평일외출",
                "평일에 하는 외출이 아닌 예외 상황인 평일 (예: 시험 끝난 후)에 가능한 외출입니다. " +
                        "부모님 허락을 맡은 후 선생님이 확인하시면 외출이 가능합니다.",
                applyGoingModel.workdayList.size
            )
        )

        ApplyGoingLogData.saturdayItemList = applyGoingModel.saturdayList
        ApplyGoingLogData.sundayItemList = applyGoingModel.sundayList
        ApplyGoingLogData.workdayItemList = applyGoingModel.workdayList
    }

    fun setShortToast(text: String) {
        if (createShortToastSingleLiveEvent.value != text)
            createShortToastSingleLiveEvent.value = text
    }

    fun applyGoingClickDoc() = applyGoingDocSingleLiveEvent.call()

    fun setApplyGoingLog(view: View) {
        applyGoingLogSingleLiveEvent .value = view
    }
}
