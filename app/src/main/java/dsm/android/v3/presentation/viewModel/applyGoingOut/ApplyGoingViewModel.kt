package dsm.android.v3.presentation.viewModel.applyGoingOut

import android.arch.lifecycle.*
import dsm.android.v3.data.remote.api
import dsm.android.v3.data.entity.ApplyGoingOutModel
import dsm.android.v3.ui.activity.applyGoingOut.ApplyGoingContract
import dsm.android.v3.ui.activity.applyGoingOutLog.ApplyGoingLogData.saturdayItemList
import dsm.android.v3.ui.activity.applyGoingOutLog.ApplyGoingLogData.sundayItemList
import dsm.android.v3.ui.activity.applyGoingOutLog.ApplyGoingLogData.workdayItemList
import dsm.android.v3.util.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ApplyGoingViewModel(val contract: ApplyGoingContract): ViewModel(), LifecycleCallback{

    override fun apply(event: Lifecycle.Event) {
        when(event) {
            Lifecycle.Event.ON_START -> {
                api.getGoingOutInfo().enqueue(object: Callback<ApplyGoingOutModel>{
                    override fun onResponse(call: Call<ApplyGoingOutModel>, response: Response<ApplyGoingOutModel>) {
                        when(response.code()){
                            200 -> setApplyGoingData(response.body()!!)
                            204 -> {
                                val applyGoingList =
                                    ApplyGoingOutModel(ArrayList(), ArrayList(), ArrayList())
                                setApplyGoingData(applyGoingList)
                                contract.createShortToast("외출신청 정보가 없습니다.")
                            }
                            403 -> contract.createShortToast("외출신청 정보 조회 권한이 없습니다.")
                            500 -> contract.createShortToast("로그인이 필요합니다.")
                            else -> contract.createShortToast("오류코드: ${response.code()}")
                        }
                    }

                    override fun onFailure(call: Call<ApplyGoingOutModel>, t: Throwable) {
                        contract.createShortToast("오류가 발생했습니다.")
                    }
                })
                contract.setViewPager(saturdayItemList.size, sundayItemList.size ,workdayItemList.size)
            }
        }
    }

    fun setApplyGoingData(applyGoingList: ApplyGoingOutModel) {
        saturdayItemList = applyGoingList.saturdayList
        sundayItemList = applyGoingList.sundayList
        workdayItemList = applyGoingList.workdayList
        contract.setViewPager(saturdayItemList.size, sundayItemList.size, workdayItemList.size)
    }

    fun applyGoingClickDoc() = contract.intentApplyGoingDoc()
}
