package dsm.android.v3.adapter

import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import dsm.android.v3.R
import dsm.android.v3.model.ApplyGoingLogItemModel
import dsm.android.v3.ui.applyGoing.ApplyGoingContract
import org.jetbrains.anko.backgroundResource
import org.jetbrains.anko.find
import org.jetbrains.anko.textColor
import org.jetbrains.anko.textColorResource

class ApplyGoingLogAdapter (val models: ArrayList<ApplyGoingLogItemModel>, val applyGoingLogRv: ApplyGoingContract.ApplyGoingLogRv): RecyclerView.Adapter<ApplyGoingLogAdapter.ApplyGoingLogViewHolder>(){

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
        val cardLayout = itemView.find<LinearLayout>(R.id.applyGoing_log_card_lay)
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
            cardLayout.backgroundResource = R.drawable.radius_dialog_white
        }

        fun itemClickedFalse(){
            clicked = true
            title.textColorResource = R.color.colorWhite
            timeLimit.textColor = Color.parseColor("#c6eded")
            reason.textColor = Color.parseColor("#8cdbdb")
            cardLayout.backgroundResource = R.drawable.radius_view_primary
        }
    }
}