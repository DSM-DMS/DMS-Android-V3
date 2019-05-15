package dsm.android.v3.ui.dialogFragment.notice

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dagger.android.support.DaggerFragment
import dsm.android.v3.R
import dsm.android.v3.presentation.di.scope.FragmentScope
import dsm.android.v3.ui.activity.notice.NoticeActivity
import dsm.android.v3.ui.CustomView.CustomCardView

@FragmentScope
class NoticeFragment : DaggerFragment(){

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