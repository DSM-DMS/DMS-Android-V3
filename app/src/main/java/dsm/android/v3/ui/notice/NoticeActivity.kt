package dsm.android.v3.ui.notice

import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.constraint.ConstraintSet
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.telecom.Call
import android.transition.TransitionManager
import android.util.Log
import android.view.View
import android.widget.Toast
import dsm.android.v3.R
import dsm.android.v3.adapter.NoticeRVAdapter
import dsm.android.v3.connecter.Connecter
import dsm.android.v3.model.NoticeListModel
import dsm.android.v3.ui.customView.CustomCardView
import kotlinx.android.synthetic.main.activity_notice_list.*
import org.bouncycastle.asn1.x500.style.RFC4519Style.l
import org.jetbrains.anko.backgroundColor
import retrofit2.Response
import javax.security.auth.callback.Callback

class NoticeActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notice_list)

        val type = intent.extras.get("activityType") as Boolean

        if (type) {
            val customView = notice_list_customview as CustomCardView
            customView.setCustomText("공지사항", "사감부에서 게시한 공지사항을 열람합니다.")
            getNotice()
        } else {

        }

        val test = ArrayList<NoticeListModel>()
        test.add(NoticeListModel("hello", "2019-3-1", "기숙사 공지사항"))
        test.add(NoticeListModel("hello", "2019-3-1", "기숙사 공지사항"))
        test.add(NoticeListModel("hello", "2019-3-1", "기숙사 공지사항"))
        test.add(NoticeListModel("hello", "2019-3-1", "기숙사 공지사항"))

        val adapter = NoticeRVAdapter(this, test)

        notice_list_rv.layoutManager = LinearLayoutManager(applicationContext)
        notice_list_rv.adapter = adapter
        adapter.setClickListener(this as View.OnClickListener)

        notice_list_customview.setOnClickListener {
            this.finish()
        }
    }

    private fun getNotice() {
        Connecter.api.getNoticeList().enqueue(object : retrofit2.Callback<NoticeListModel> {

            override fun onResponse(call: retrofit2.Call<NoticeListModel>, response: Response<NoticeListModel>) {
                val body = response.body()
                Log.d("tag", body!!.date)
            }

            override fun onFailure(call: retrofit2.Call<NoticeListModel>, t: Throwable) {
                Log.d("tag", t.message)
            }
        })
    }

    fun getRules() {

    }

    var check = true

    fun setVisible() {
        if (check) {
            notice_list_customview.visibility = View.INVISIBLE
            notice_list_rv.visibility = View.INVISIBLE
            notice_list_constraint1.backgroundColor = ContextCompat.getColor(this, R.color.colorGray300)
            check = false
        } else {
            notice_list_customview.visibility = View.VISIBLE
            notice_list_rv.visibility = View.VISIBLE
            notice_list_constraint1.backgroundColor = ContextCompat.getColor(this, R.color.colorWhite)
            check = true

        }
    }


    override fun onClick(v: View?) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.setCustomAnimations(R.anim.slideup_in, R.anim.slideup_out)
        transaction.add(R.id.notice_list_description_fragment, NoticeDescriptionFragment())
        transaction.commit()
        setVisible()
    }
}