package dsm.android.v3.presentation.model

import dsm.android.v3.domain.entity.applyGoingOut.ApplyGoingOutModel

object ApplyGoingLogData{

    var saturdayItemList = ArrayList<ApplyGoingOutModel.ApplyGoingDataModel>()

    var sundayItemList = ArrayList<ApplyGoingOutModel.ApplyGoingDataModel>()

    var workdayItemList = ArrayList<ApplyGoingOutModel.ApplyGoingDataModel>()

    lateinit var deleteItem: ApplyGoingOutModel.ApplyGoingDataModel
}