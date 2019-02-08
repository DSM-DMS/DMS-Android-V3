package dsm.android.v3.ui.applyGoing

import android.arch.lifecycle.MutableLiveData
import dsm.android.v3.model.ApplyGoingLogItemModel

object ApplyGoingLogData{
    val saturdayItemList: ArrayList<ApplyGoingLogItemModel> = arrayListOf(
        ApplyGoingLogItemModel("2019.1.11 ~ 2019.1.12", "외출에 이유가 있나요?"),
        ApplyGoingLogItemModel("2019.1.12 ~ 2019.1.13", "탈대마1"),
        ApplyGoingLogItemModel("2019.1.12 ~ 2019.1.13", "탈대마2"),
        ApplyGoingLogItemModel("2019.1.12 ~ 2019.1.13", "탈대마3"),
        ApplyGoingLogItemModel("2019.1.12 ~ 2019.1.13", "탈대마4"))

    val sundayItemList: ArrayList<ApplyGoingLogItemModel> = arrayListOf(
        ApplyGoingLogItemModel("2019.1.11 ~ 2019.1.12", "외출에 이유가 있나요?"),
        ApplyGoingLogItemModel("2019.1.12 ~ 2019.1.13", "탈대마1"),
        ApplyGoingLogItemModel("2019.1.12 ~ 2019.1.13", "탈대마2"),
        ApplyGoingLogItemModel("2019.1.12 ~ 2019.1.13", "탈대마3"),
        ApplyGoingLogItemModel("2019.1.12 ~ 2019.1.13", "탈대마4"))

    val workdayItemList: ArrayList<ApplyGoingLogItemModel> = arrayListOf(
        ApplyGoingLogItemModel("2019.1.11 ~ 2019.1.12", "외출에 이유가 있나요?"),
        ApplyGoingLogItemModel("2019.1.12 ~ 2019.1.13", "탈대마1"),
        ApplyGoingLogItemModel("2019.1.12 ~ 2019.1.13", "탈대마2"),
        ApplyGoingLogItemModel("2019.1.12 ~ 2019.1.13", "탈대마3"),
        ApplyGoingLogItemModel("2019.1.12 ~ 2019.1.13", "탈대마4"))

    val deleteData = ArrayList<ApplyGoingLogItemModel>()
}