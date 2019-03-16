package dsm.android.v3.ui.notice

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import dsm.android.v3.R
import dsm.android.v3.connecter.Connecter
import dsm.android.v3.model.NoticeDescriptionModel
import kotlinx.android.synthetic.main.fragment_notice_description.*
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

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
        activity!!.supportFragmentManager.beginTransaction().remove(this).commit()
    }

    fun getNotice(id : Int) {

        Connecter.api.getNoticeDescription(id.toString()).enqueue(object : retrofit2.Callback<NoticeDescriptionModel> {

            override fun onResponse(call: Call<NoticeDescriptionModel>, response: Response<NoticeDescriptionModel>) {
                var body = response.body()

                notice_description_description_tv.text = body!!.postDate
                notice_description_content_tv.text = body!!.postDate
                notice_description_title_tv.text = body!!.title
            }

            override fun onFailure(call: Call<NoticeDescriptionModel>, t: Throwable) {
                Log.d("tag", t.message)
            }
        })
    }

    fun getRules(id : Int) {

        Connecter.api.getNoticeDescription(id.toString()).enqueue(object : retrofit2.Callback<NoticeDescriptionModel> {

            override fun onResponse(call: Call<NoticeDescriptionModel>, response: Response<NoticeDescriptionModel>) {
                var body = response.body()

                notice_description_description_tv.text = body!!.postDate
                notice_description_content_tv.text = body!!.postDate
                notice_description_title_tv.text = body!!.title
            }

            override fun onFailure(call: Call<NoticeDescriptionModel>, t: Throwable) {
                Log.d("tag", t.message)
            }
        })
    }
}