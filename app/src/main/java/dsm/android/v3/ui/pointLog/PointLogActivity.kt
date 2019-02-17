package dsm.android.v3.ui.pointLog

import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager
import dsm.android.v3.R
import dsm.android.v3.adapter.PointLogAdapter
import dsm.android.v3.model.PointLogItemModel

import kotlinx.android.synthetic.main.activity_point_log.*

class PointLogActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_point_log)
        setSupportActionBar(pointLog_toolbar)

        pointLog_toolbar.setTitleTextColor(Color.parseColor("#FFFFFF"))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.title = "상벌점 내역 조회"

        pointLog_toolbar.setNavigationOnClickListener {
            finish()
        }
        val models = arrayListOf(PointLogItemModel("호실 내 음식물 반입","2019-01-01",3,true),
            PointLogItemModel("호실 내 음식물 반입","2019-01-01",3,true),
            PointLogItemModel("호실 내 음식물 반입","2019-01-01",3,true),
            PointLogItemModel("호실 내 음식물 반입","2019-01-01",3,true),
            PointLogItemModel("호실 내 음식물 반입","2019-01-01",3,true),
            PointLogItemModel("호실 내 음식물 반입","2019-01-01",3,true),
            PointLogItemModel("호실 내 음식물 반입","2019-01-01",3,true),
            PointLogItemModel("호실 내 음식물 반입","2019-01-01",3,true),
            PointLogItemModel("호실 내 음식물 반입","2019-01-01",3,true),
            PointLogItemModel("호실 내 음식물 반입","2019-01-01",3,true),
            PointLogItemModel("호실 내 음식물 반입","2019-01-01",3,false)
        )
        pointLog_list_rv.layoutManager = LinearLayoutManager(this)
        pointLog_list_rv.adapter = PointLogAdapter(models)


    }

}
