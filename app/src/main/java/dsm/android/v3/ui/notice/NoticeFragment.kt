package dsm.android.v3.ui.notice

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.constraint.ConstraintSet
import android.support.v4.app.DialogFragment
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.transition.TransitionManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.gson.JsonObject
import dsm.android.v3.R
import dsm.android.v3.adapter.NoticeRVAdapter
import dsm.android.v3.connecter.Connecter
import dsm.android.v3.model.NoticeListModel
import dsm.android.v3.ui.customView.CustomCardView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_notice_list.*
import kotlinx.android.synthetic.main.fragment_notice_main.*
import org.jetbrains.anko.startActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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