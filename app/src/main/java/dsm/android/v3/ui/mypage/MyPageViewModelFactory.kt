package dsm.android.v3.ui.mypage

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.content.Context

class MyPageViewModelFactory(val contract: MyPageContract?, val context: Context) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T
            = modelClass.getConstructor(MyPageContract::class.java, Context::class.java).newInstance(contract, context)
}