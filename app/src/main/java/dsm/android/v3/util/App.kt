package dsm.android.v3.util

import android.app.Application
import android.content.Context

class App: Application(){
    companion object {
        private var instance: App? = null

        fun getContext(): Context? {
            return instance
        }
    }

    override fun onCreate() {
        instance = this
        super.onCreate()
    }
}
