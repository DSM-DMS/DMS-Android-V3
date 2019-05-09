package dsm.android.v3.ui.activity.applyGoingOut

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import dsm.android.v3.R
import kotlinx.android.synthetic.main.activity_apply_going_out.*

class ApplyGoingOutActivity : AppCompatActivity() {

    private val controller by lazy { findNavController(R.id.going_out_fragment) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_apply_going_out)

        setSupportActionBar(going_out_toolbar)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        going_out_toolbar.setNavigationOnClickListener { onBackPressed() }

        setupActionBarWithNavController(controller)
    }
}