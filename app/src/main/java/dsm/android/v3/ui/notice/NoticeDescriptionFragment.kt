package dsm.android.v3.ui.notice

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import dsm.android.v3.R

class NoticeDescriptionFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.fragment_notice_description, container, false) as View
        val noticeActivity = activity as NoticeActivity

        view.findViewById<ImageView>(R.id.notice_description_cancle_iv).setOnClickListener {
            cancle(noticeActivity)
        }
        return view
    }

    fun cancle(noticeActivity: NoticeActivity) {
        noticeActivity.setVisible()
        activity!!.supportFragmentManager.beginTransaction().remove(this).commit()
    }
}