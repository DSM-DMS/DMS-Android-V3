package dsm.android.v3.util

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.v7.app.AppCompatActivity


abstract class DataBindingActivity<T : ViewDataBinding> : AppCompatActivity() {

    lateinit var binding: T

    abstract val layoutId: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, layoutId)
        binding.setLifecycleOwner(this)
    }
}
