package dsm.android.v3.ui.applyGoing

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.content.res.Resources
import dsm.android.v3.R

class ApplyGoingViewModel(): ViewModel(){

    private var applyGoingContract: ApplyGoingContract? = null

    val countList = MutableLiveData<String>()
    var itemTitleText = MutableLiveData<String>()
    var itemExplanationText = MutableLiveData<String>()

    constructor(contract: ApplyGoingContract) : this(){
        applyGoingContract = contract
    }

    fun pagerTextSet(itemTitleText: String, itemExplanationText: String){
        this.itemTitleText.value = itemTitleText
        this.itemExplanationText.value = itemExplanationText
    }
}