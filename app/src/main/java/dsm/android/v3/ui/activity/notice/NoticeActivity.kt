package dsm.android.v3.ui.activity.notice

import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import android.widget.Toast
import dsm.android.v3.R
import dsm.android.v3.ui.adapter.Notice.NoticeRVAdapter
import dsm.android.v3.ui.adapter.Notice.RulesRvAdpater
import dsm.android.v3.data.remote.Connecter
import dsm.android.v3.data.entity.NoticeListModel
import dsm.android.v3.data.entity.RulesModel
import dsm.android.v3.ui.fragment.notice.NoticeDescriptionFragment
import dsm.android.v3.ui.customView.CustomCardView
import kotlinx.android.synthetic.main.activity_notice_list.*
import org.jetbrains.anko.backgroundColor
import retrofit2.Response

class NoticeActivity : AppCompatActivity() {

    var type = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notice_list)

        val type = intent.extras.get("activityType") as Boolean
        this.type = type

        val customView = notice_list_customview as CustomCardView

        if (type) {
            customView.setCustomText("공지사항", "사감부에서 게시한 공지사항을 열람합니다.")
            getNotice(this)
        } else {
            customView.setCustomText("기숙사 규정", "기숙사 규정을 열람합니다.")
            getRules(this)
        }

        notice_list_customview.setOnClickListener {
            this.finish()
        }
    }

    private fun getNotice(activity : NoticeActivity) {
        Connecter.api.getNoticeList().enqueue(object : retrofit2.Callback<NoticeListModel>{

            override fun onResponse(call: retrofit2.Call<NoticeListModel>, response: Response<NoticeListModel>) {
                val body = response.body()!!

                val adapter = NoticeRVAdapter(baseContext, body, activity)

                notice_list_rv.layoutManager = LinearLayoutManager(applicationContext)
                notice_list_rv.adapter = adapter
            }

            override fun onFailure(call: retrofit2.Call<NoticeListModel>, t: Throwable) {
                Log.d("tag", t.message)
                Toast.makeText(baseContext, "네트워크를 확인해주세요", Toast.LENGTH_SHORT).show()
            }
        })
    }

    fun getRules(activity: NoticeActivity) {
        Connecter.api.getRulesList().enqueue(object : retrofit2.Callback<RulesModel> {
            override fun onResponse(call: retrofit2.Call<RulesModel>, response: Response<RulesModel>) {
                val body = response.body()!!

                val adapter = RulesRvAdpater(baseContext, body, activity)

                notice_list_rv.layoutManager = LinearLayoutManager(applicationContext)
                notice_list_rv.adapter = adapter
            }

            override fun onFailure(call: retrofit2.Call<RulesModel>, t: Throwable) {
                Log.d("tag", t.message)
                Toast.makeText(baseContext, "네트워크를 확인해주세요", Toast.LENGTH_SHORT).show()
            }
        })
    }

    var check = true

    fun setVisible() {
        if (check) {
            notice_list_rv.visibility = View.INVISIBLE
            notice_list_customview.visibility = View.INVISIBLE
            notice_list_constraint1.backgroundColor = ContextCompat.getColor(this, R.color.colorGray300)
            check = false
        } else {
            notice_list_customview.visibility = View.VISIBLE
            notice_list_rv.visibility = View.VISIBLE
            notice_list_constraint1.backgroundColor = ContextCompat.getColor(this, R.color.colorWhite)
            check = true

        }
    }

    val fragment = NoticeDescriptionFragment()

    fun createDescription (id : Int, type : Boolean) {
        setVisible()

        val bundle = Bundle(2)
        bundle.putBoolean("type", type)
        bundle.putInt("id", id)
        fragment.arguments = bundle

        val transaction = supportFragmentManager.beginTransaction()
        transaction.setCustomAnimations(R.anim.slideup_in, R.anim.slideup_out)
        transaction.replace(R.id.notice_list_description_fragment, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    fun frameDate(date : String) :String {
        var frameDate = date.substring(0, 10)
        return frameDate
    }

    override fun onBackPressed() {
        if(!check) fragment.cancle(this)
        super.onBackPressed()
    }
}