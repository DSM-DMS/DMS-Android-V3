package dsm.android.v3.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import dsm.android.v3.R
import dsm.android.v3.model.ApplyGoingModel
import dsm.android.v3.ui.applyGoingLog.ApplyGoingLogContract
import org.jetbrains.anko.*
import java.text.SimpleDateFormat

class ApplyGoingLogAdapter (val models: ArrayList<ApplyGoingModel.ApplyGoingDataModel>, val applyGoingLogRv: ApplyGoingLogContract.ApplyGoingLogRv): RecyclerView.Adapter<ApplyGoingLogAdapter.ApplyGoingLogViewHolder>(){

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ApplyGoingLogViewHolder {
        val view = LayoutInflater.from(p0.context).inflate(R.layout.item_apply_going_log, p0, false)
        return ApplyGoingLogViewHolder(view)
    }

    override fun getItemCount(): Int = models.size

    override fun onBindViewHolder(p0: ApplyGoingLogViewHolder, p1: Int) = p0.bind(models[p1])

    inner class ApplyGoingLogViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        private val dateFormat = SimpleDateFormat("MM월 dd일 hh:mm ~ hh:mm")
        val timeLimit = itemView.find<TextView>(R.id.applyGoing_log_card_time_tv)
        val reason = itemView.find<TextView>(R.id.applyGoing_log_card_reason_tv)

        fun bind(model: ApplyGoingModel.ApplyGoingDataModel){
            timeLimit.text = dateFormat.format(model.goOutDate)
            reason.text = model.reason
            itemView.setOnClickListener { applyGoingLogRv.logItemClick(model) }
        }
    }
}