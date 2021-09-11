package dsm.android.v3.ui.activity.setting

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import dsm.android.v3.R
import dsm.android.v3.data.local.shared.LocalStorage
import dsm.android.v3.databinding.ActivitySettingBinding
import dsm.android.v3.presentation.viewModel.setting.SettingViewModel
import dsm.android.v3.presentation.viewModel.setting.SettingViewModelFactory
import dsm.android.v3.util.DataBindingActivity
import dsm.android.v3.util.ThemeUtil
import kotlinx.android.synthetic.main.activity_setting.*
import javax.inject.Inject

class SettingActivity : DataBindingActivity<ActivitySettingBinding>() {
    override val layoutId: Int
        get() = R.layout.activity_setting

    @Inject
    lateinit var viewModelFactory: SettingViewModelFactory

    @Inject
    lateinit var localStorage: LocalStorage

    private val themeUtil by lazy {
        ThemeUtil(localStorage)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(setting_toolbar)
        val viewModel =
            ViewModelProviders.of(this, viewModelFactory).get(SettingViewModel::class.java)
        binding.vm = viewModel
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        title = "설정"
        binding.settingToolbar.setNavigationOnClickListener {
            onBackPressed()
        }

        viewModel.type.observe(this, {
            themeUtil.applyTheme(it ?: 0)
            recreate()
        })
    }


}