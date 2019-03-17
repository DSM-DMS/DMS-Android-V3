package dsm.android.v3.adapter

import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import dsm.android.v3.R
import dsm.android.v3.model.PointLogItemModel
import org.jetbrains.anko.find

class PointLogAdapter(val models: ArrayList<PointLogItemModel>) :
    RecyclerView.Adapter<PointLogAdapter.PointLogViewHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): PointLogViewHolder {
        val view = LayoutInflater.from(p0.context).inflate(R.layout.item_point_log, p0, false)
        return PointLogViewHolder(view)
    }

    override fun getItemCount() = models.size

    override fun onBindViewHolder(p0: PointLogViewHolder, p1: Int) = p0.bind(models[p1])

    class PointLogViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title = itemView.find<TextView>(R.id.pointLogItem_title_tv)
        val date = itemView.find<TextView>(R.id.pointLogItem_date_tv)
        val point = itemView.find<TextView>(R.id.pointLogItem_point_tv)
        fun bind(model: PointLogItemModel) {
            title.text = model.reason
            date.text = model.date
            point.text = if (model.point > 0) "+${model.point}"
                else "${model.point}"
            if (model.pointType) point.setTextColor(ContextCompat.getColor(title.context, R.color.colorPrimary))
            else point.setTextColor(ContextCompat.getColor(title.context, R.color.colorRed))
        }
    }
}