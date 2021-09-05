package dsm.android.v3.ui.activity.setting

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import dsm.android.v3.R
import dsm.android.v3.databinding.ActivitySettingBinding
import dsm.android.v3.util.DataBindingActivity
import kotlinx.android.synthetic.main.activity_apply_meal.*
import kotlinx.android.synthetic.main.activity_setting.*

class SettingActivity : DataBindingActivity<ActivitySettingBinding>() {
    override val layoutId: Int
        get() = R.layout.activity_setting
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(setting_toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        title = "설정"
        binding.settingToolbar.setNavigationOnClickListener {
            onBackPressed()
        }
    }
}