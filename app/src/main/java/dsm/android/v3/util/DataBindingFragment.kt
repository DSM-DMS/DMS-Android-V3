package dsm.android.v3.util

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.LifecycleRegistry
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.trello.rxlifecycle2.components.support.RxFragment
import dagger.android.support.DaggerFragment
import dagger.android.support.HasSupportFragmentInjector
import dsm.android.v3.presentation.di.app.BaseApp

abstract class DataBindingFragment<T : ViewDataBinding> : DaggerFragment(), HasSupportFragmentInjector {

    lateinit var rootView: View
    lateinit var binding: T

    abstract val layoutId: Int

    private val lifecycleOwner = LifecycleOwner()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        rootView = binding.root
        binding.lifecycleOwner = this
        return rootView
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

    fun register(callback : LifecycleCallback) {
        lifecycleOwner.register(callback)
    }

    fun unregister(callback : LifecycleCallback) {
        lifecycleOwner.unregister(callback)
    }

    private fun notifyEvent(event : Lifecycle.Event) {
        lifecycleOwner.notifyEvent(event)
    }

}