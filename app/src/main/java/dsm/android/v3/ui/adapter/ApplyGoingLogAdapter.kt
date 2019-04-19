package dsm.android.v3.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import dsm.android.v3.R
import dsm.android.v3.data.entity.ApplyGoingOut
import dsm.android.v3.ui.activity.applyGoingOutLog.ApplyGoingLogContract
import org.jetbrains.anko.*
import java.text.SimpleDateFormat
import java.util.*

class ApplyGoingLogAdapter (val models: ArrayList<ApplyGoingOut.ApplyGoingDataModel>, val applyGoingLogRv: ApplyGoingLogContract.ApplyGoingLogRv): RecyclerView.Adapter<ApplyGoingLogAdapter.ApplyGoingLogViewHolder>(){

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ApplyGoingLogViewHolder {
        val view = LayoutInflater.from(p0.context).inflate(R.layout.item_apply_going_log, p0, false)
        return ApplyGoingLogViewHolder(view)
    }

    override fun getItemCount(): Int = models.size

    override fun onBindViewHolder(p0: ApplyGoingLogViewHolder, p1: Int) = p0.bind(models[p1])

    inner class ApplyGoingLogViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        private val stringFormat = SimpleDateFormat("MM월 dd일 HH:mm")
        private val dateFormat = SimpleDateFormat("MM-dd HH:mm")
        val timeLimit = itemView.find<TextView>(R.id.applyGoing_log_card_time_tv)
        val reason = itemView.find<TextView>(R.id.applyGoing_log_card_reason_tv)

        fun bind(model: ApplyGoingOut.ApplyGoingDataModel){
            timeLimit.text = "${createFrontDate(model.date)} ${createBackDate(model.date)}"
            reason.text = model.reason
            itemView.setOnClickListener { applyGoingLogRv.logItemClick(model) }
        }

        fun createFrontDate(date: String): String = stringFormat.format(dateFormat.parse(date))
        fun createBackDate(date: String): String {
            val idx = date.indexOf("~")
            return date.substring(idx)
        }
    }
}