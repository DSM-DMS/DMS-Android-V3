package dsm.android.v3.ui.activity.setting

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatDelegate
import dsm.android.v3.R
import dsm.android.v3.databinding.ActivitySettingBinding
import dsm.android.v3.presentation.viewModel.setting.SettingViewModel
import dsm.android.v3.presentation.viewModel.setting.SettingViewModelFactory
import dsm.android.v3.util.DataBindingActivity
import kotlinx.android.synthetic.main.activity_setting.*
import javax.inject.Inject

class SettingActivity : DataBindingActivity<ActivitySettingBinding>() {
    override val layoutId: Int
        get() = R.layout.activity_setting

    @Inject
    lateinit var viewModelFactory: SettingViewModelFactory

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

        viewModel.type.observe(this,{
            when(it){
                1->{
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                    onStart()
                }
                2->{
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                    onStart()
                }
                else->{
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_AUTO)
                    onStart()
                }
            }
        })
    }
}