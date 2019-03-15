package dsm.android.v3.ui.notice

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import dsm.android.v3.R
import dsm.android.v3.model.NoticeListModel
import org.jetbrains.anko.support.v4.act
import java.util.zip.Inflater

class NoticeDescriptionFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.fragment_notice_description, container, false) as View
        val noticeActivity = activity as NoticeActivity

        view.findViewById<ImageView>(R.id.notice_description_cancle_iv).setOnClickListener {
            noticeActivity.setVisible()
            activity!!.supportFragmentManager.beginTransaction().setCustomAnimations(R.anim.slideup_in, R.anim.slideup_out)
            activity!!.supportFragmentManager.beginTransaction().remove(this).commit()
        }
        return view
    }
}