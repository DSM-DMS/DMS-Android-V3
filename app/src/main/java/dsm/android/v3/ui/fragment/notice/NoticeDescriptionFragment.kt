package dsm.android.v3.ui.fragment.notice

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import dagger.android.support.DaggerFragment
import dsm.android.v3.R
import dsm.android.v3.domain.repository.notice.NoticeRepository
import dsm.android.v3.presentation.di.scope.ActivityScope
import dsm.android.v3.ui.activity.notice.NoticeActivity
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.fragment_notice_description.*
import javax.inject.Inject

@ActivityScope
class NoticeDescriptionFragment : DaggerFragment() {

    val composite = CompositeDisposable()

    @Inject
    lateinit var repository: NoticeRepository

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.fragment_notice_description, container, false) as View
        val noticeActivity = activity as NoticeActivity
        val id = arguments!!.getInt("id")
        val type = arguments!!.getBoolean("type")

        if(type) getNotice(id)
        else getRules(id)

        view.findViewById<ImageView>(R.id.notice_description_cancle_iv).setOnClickListener {
            cancel(noticeActivity)
        }

        return view
    }

    fun cancel(noticeActivity: NoticeActivity) {
        noticeActivity.setVisible()
        noticeActivity.type = true
        activity!!.supportFragmentManager.beginTransaction().remove(this).commit()
    }

    fun getNotice(id : Int) {
        composite.add(repository.getNoticeDescription(id.toString())
            .subscribe({ response ->
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
            }, {
                Log.d("tag", it.message.toString())
            }))
    }

    fun getRules(id : Int) {
        composite.add(repository.getRulesDescription(id.toString())
            .subscribe({ response ->
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
            }, {
                Log.d("tag", it.message.toString())
            }))
    }

    fun frameDate(date : String) :String {
        var day = date.substring(8, 10)
        var month = date.substring(5,7)
        var year = date.substring(0, 4)
        return "사감부에서 ${year}년 ${month}월 ${day}일에 게시한 글입니다."
    }

    override fun onDestroy() {
        super.onDestroy()
        composite.clear()
    }
}