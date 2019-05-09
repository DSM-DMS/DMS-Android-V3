package dsm.android.v3.presentation.viewModel.applyGoingOutLog

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import dsm.android.v3.domain.entity.ApplyGoingOutModel
import dsm.android.v3.presentation.model.ApplyGoingLogData
import dsm.android.v3.presentation.model.ApplyGoingLogData.deleteItem
import dsm.android.v3.util.SingleLiveEvent
import java.text.SimpleDateFormat

class ApplyGoingLogViewModel(title: String) : ViewModel() {

    val logTitle = MutableLiveData<String>()
    val logItems = MutableLiveData<ArrayList<ApplyGoingOutModel.ApplyGoingDataModel>>()

    val logItemClickSingleLiveEvent = SingleLiveEvent<Any>()

    private val stringFormat = SimpleDateFormat("MM월 dd일 HH:mm")
    private val dateFormat = SimpleDateFormat("MM-dd HH:mm")

    init {
        logTitle.value = title

        when (logTitle.value) {
            "토요외출" -> logItems.value = ApplyGoingLogData.saturdayItemList
            "일요외출" -> logItems.value = ApplyGoingLogData.sundayItemList
            "평일외출" -> logItems.value = ApplyGoingLogData.workdayItemList
        }
    }

    fun createDate(date: String): String {
        val front = stringFormat.format(dateFormat.parse(date))
        val idx = date.indexOf("~")
        val back = date.substring(idx)

        return "$front $back"
    }

    fun logItemClick(log: ApplyGoingOutModel.ApplyGoingDataModel) {
        deleteItem = log
        logItemClickSingleLiveEvent.call()
    }
}