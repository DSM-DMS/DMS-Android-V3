package dsm.android.v3.ui.fragment.notice

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dsm.android.v3.R
import dsm.android.v3.ui.customView.CustomCardView
import dsm.android.v3.ui.activity.notice.NoticeActivity

class NoticeFragment : DialogFragment(){

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater!!.inflate(R.layout.fragment_notice_main, container, false) as View

        view.findViewById<CustomCardView>(R.id.notice_notice_customview).setOnClickListener {
            val intent = Intent(context, NoticeActivity::class.java)
            intent.putExtra("activityType", true)
            context!!.startActivity(intent)
        }

        view.findViewById<CustomCardView>(R.id.notice_rules_customview).setOnClickListener {
            val intent = Intent(context, NoticeActivity::class.java)
            intent.putExtra("activityType", false)
            context!!.startActivity(intent)
        }

        return view
    }

}