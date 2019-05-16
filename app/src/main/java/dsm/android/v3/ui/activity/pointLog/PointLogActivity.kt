package dsm.android.v3.ui.activity.pointLog

import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import dagger.android.support.DaggerAppCompatActivity
import dsm.android.v3.R
import dsm.android.v3.data.remote.ApiClient
import dsm.android.v3.ui.adapter.PointLogAdapter
import dsm.android.v3.domain.entity.PointLogResponseModel
import dsm.android.v3.domain.repository.pointLog.PointLogRepository
import dsm.android.v3.domain.repository.pointLog.PointLogRepositoryImpl
import dsm.android.v3.presentation.di.app.BaseApp
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_point_log.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class PointLogActivity : DaggerAppCompatActivity() {


    val composite = CompositeDisposable()

    @Inject
    lateinit var repository: PointLogRepository

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

        composite.add(repository.getPointLog()
            .subscribe({ response ->
                val body = response.body()
                pointLog_list_rv.layoutManager = LinearLayoutManager(this@PointLogActivity)
                pointLog_list_rv.adapter = body?.pointHistory?.let { PointLogAdapter(it) }
            }, {
                Log.d("throwable", "$it")
            }))
    }

    override fun onDestroy() {
        super.onDestroy()
        composite.clear()
    }
}
