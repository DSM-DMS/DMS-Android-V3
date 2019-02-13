package dsm.android.v3.ui.applyStaying

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.view.View

class ApplyStayingViewModel(val contract: ApplyStayingContract): ViewModel(){

    private val clickedView = MutableLiveData<View>()

    init {
        // 서버에서 신청된 거 요일 가져와서
        // clickedView에 넣어주고 changeColor()해주면 됨
    }

    fun applyBtnClick(){
        val view = contract.viewGroup.getChildAt(contract.getCurrentItem())
        if(view != clickedView.value){
            if(clickedView.value != null){
                contract.originalColor(clickedView.value!!)
            }
            clickedView.value = view
            contract.changeColor(view)
            // 서버에 신청하기 보냄 뭘 신청하는지는 CurrentItem으로 구분
        }
    }
}