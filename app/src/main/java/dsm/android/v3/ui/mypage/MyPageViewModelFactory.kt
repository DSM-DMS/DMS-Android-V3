package dsm.android.v3.ui.mypage

import android.app.Application
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.content.Context

class MyPageViewModelFactory(val contract: MyPageContract?, val app: Application) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T
            = modelClass.getConstructor(MyPageContract::class.java, Application::class.java).newInstance(contract, app)
}