package dsm.android.v3.ui.fragment.notice

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import dsm.android.v3.R
import dsm.android.v3.presentation.model.NoticeDescriptionModel
import dsm.android.v3.ui.activity.notice.NoticeActivity
import kotlinx.android.synthetic.main.fragment_notice_description.*
import retrofit2.Call
import retrofit2.Response

class NoticeDescriptionFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.fragment_notice_description, container, false) as View
        val noticeActivity = activity as NoticeActivity
        val id = arguments!!.getInt("id")
        val type = arguments!!.getBoolean("type")

        if(type) getNotice(id)
        else getRules(id)

        view.findViewById<ImageView>(R.id.notice_description_cancle_iv).setOnClickListener {
            cancle(noticeActivity)
        }

        return view
    }

    fun cancle(noticeActivity: NoticeActivity) {
        noticeActivity.setVisible()
        noticeActivity.type = true
        activity!!.supportFragmentManager.beginTransaction().remove(this).commit()
    }

    fun getNotice(id : Int) {

        Connecter.api.getNoticeDescription(id.toString()).enqueue(object : retrofit2.Callback<NoticeDescriptionModel> {

            override fun onResponse(call: Call<NoticeDescriptionModel>, response: Response<NoticeDescriptionModel>) {
                when(response.code()) {
                    200 -> {
                        var body = response.body()

                        notice_description_description_tv.text = frameDate(body!!.postDate)
                        notice_description_content_tv.text = body!!.content
                        notice_description_title_tv.text = body!!.title
                    }
                    204 -> {
                        Toast.makeText(context, "다시 시도해주세요", Toast.LENGTH_SHORT).show()
                    }
                }
            }

            override fun onFailure(call: Call<NoticeDescriptionModel>, t: Throwable) {
                Log.d("tag", t.message)
            }
        })
    }

    fun getRules(id : Int) {

        Connecter.api.getRulesDescription(id.toString()).enqueue(object : retrofit2.Callback<NoticeDescriptionModel>{

            override fun onResponse(call: Call<NoticeDescriptionModel>, response: Response<NoticeDescriptionModel>) {
                when(response.code()) {
                    200 -> {
                        var body = response.body()

                        notice_description_description_tv.text = frameDate(body!!.postDate)
                        notice_description_content_tv.text = body!!.content
                        notice_description_title_tv.text = body!!.title
                    }
                    204 -> {
                        Toast.makeText(context, "다시 시도해주세요", Toast.LENGTH_SHORT).show()
                    }
                }
            }

            override fun onFailure(call: Call<NoticeDescriptionModel>, t: Throwable) {
                Log.d("tag", t.message)
            }
        })
    }

    fun frameDate(date : String) :String {
        var day = date.substring(9, 10)
        var month = date.substring(6,7)
        var year = date.substring(0, 4)
        return "사감부에서 ${year}년 ${month}월 ${day}일에 게시한 글입니다."
    }
}