package dsm.android.v3.ui.applyGoingOut

 import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import androidx.navigation.findNavController
import dsm.android.v3.R
import kotlinx.android.synthetic.main.activity_apply_going_out.*

class ApplyGoingOutActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_apply_going_out)

        setSupportActionBar(going_out_toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.title = "외출 신청"
        going_out_toolbar.setNavigationOnClickListener { onBackPressed() }

        findNavController(R.id.going_out_fragment)
    }
}