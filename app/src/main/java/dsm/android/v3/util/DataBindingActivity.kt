package dsm.android.v3.util

import android.arch.lifecycle.Lifecycle
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity


abstract class DataBindingActivity<T : ViewDataBinding> : RxAppCompatActivity() {

    lateinit var binding: T

    abstract val layoutId: Int

    private val lifecycleOwner = LifecycleOwner()

    abstract fun inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        inject()
        binding = DataBindingUtil.setContentView(this, layoutId)
        binding.lifecycleOwner = this
    }

    override fun onStart() {
        super.onStart()
        notifyEvent(Lifecycle.Event.ON_START)
    }

    override fun onResume() {
        super.onResume()
        notifyEvent(Lifecycle.Event.ON_RESUME)
    }

    override fun onPause() {
        notifyEvent(Lifecycle.Event.ON_PAUSE)
        super.onPause()
    }

    override fun onStop() {
        notifyEvent(Lifecycle.Event.ON_STOP)
        super.onStop()
    }

    override fun onDestroy() {
        notifyEvent(Lifecycle.Event.ON_DESTROY)
        super.onDestroy()
    }

    fun register(callback: LifecycleCallback) {
        lifecycleOwner.register(callback)
    }

    fun unregister(callback: LifecycleCallback) {
        lifecycleOwner.unregister(callback)
    }

    private fun notifyEvent(event: Lifecycle.Event) {
        lifecycleOwner.notifyEvent(event)
    }

}
