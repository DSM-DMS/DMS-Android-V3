package dsm.android.v3.ui.notice

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import dsm.android.v3.R
import kotlinx.android.synthetic.main.activity_notice_list.*

class NoticeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notice_list)

        notice_list_customview.setOnClickListener {
            this.finish()
        }
    }
}