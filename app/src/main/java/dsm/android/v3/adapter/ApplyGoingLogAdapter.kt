package dsm.android.v3.adapter

import android.content.Context
import android.graphics.Color
import android.support.v4.content.ContextCompat
import android.support.v7.widget.Api17CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import dsm.android.v3.R
import dsm.android.v3.model.ApplyGoingLogItemModel
import dsm.android.v3.ui.applyGoingLog.ApplyGoingLogContract
import org.jetbrains.anko.*

class ApplyGoingLogAdapter (val models: ArrayList<ApplyGoingLogItemModel>, val applyGoingLogRv: ApplyGoingLogContract.ApplyGoingLogRv): RecyclerView.Adapter<ApplyGoingLogAdapter.ApplyGoingLogViewHolder>(){

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ApplyGoingLogViewHolder {
        val view = LayoutInflater.from(p0.context).inflate(R.layout.item_apply_going_log, p0, false)
        return ApplyGoingLogViewHolder(view)
    }

    override fun getItemCount(): Int = models.size

    override fun onBindViewHolder(p0: ApplyGoingLogViewHolder, p1: Int) = p0.bind(models[p1])

    inner class ApplyGoingLogViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val title = itemView.find<TextView>(R.id.applyGoing_log_card_title_tv)
        val timeLimit = itemView.find<TextView>(R.id.applyGoing_log_card_time_tv)
        val reason = itemView.find<TextView>(R.id.applyGoing_log_card_reason_tv)
        val cardLayout = itemView.find<Api17CardView>(R.id.applyGoing_log_card_lay)
        var clicked: Boolean = false

        fun bind(model: ApplyGoingLogItemModel){
            timeLimit.text = model.time
            reason.text = model.reason
            itemView.setOnClickListener {
                if (clicked) {
                    itemClickedTrue()
                    applyGoingLogRv.logItemClickTrue(model)
                }
                else {
                    itemClickedFalse()
                    applyGoingLogRv.logItemClickFalse(model)
                }
            }
        }

        fun itemClickedTrue(){
            clicked = false
            title.textColorResource =  R.color.colorGray700
            timeLimit.textColorResource = R.color.colorGray600
            reason.textColorResource = R.color.colorGray400
            cardLayout.setCardBackgroundColor(ContextCompat.getColor(applyGoingLogRv as Context, R.color.colorWhite))
        }

        fun itemClickedFalse(){
            clicked = true
            title.textColorResource = R.color.colorWhite
            timeLimit.textColor = Color.parseColor("#c6eded")
            reason.textColor = Color.parseColor("#8cdbdb")
            cardLayout.setCardBackgroundColor(ContextCompat.getColor(applyGoingLogRv as Context, R.color.colorPrimary))
        }
    }
}