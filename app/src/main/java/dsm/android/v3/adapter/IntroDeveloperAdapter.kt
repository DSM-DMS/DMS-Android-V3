package dsm.android.v3.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import dsm.android.v3.R
import dsm.android.v3.model.IntroDeveloperModel
import org.jetbrains.anko.find

class IntroDeveloperAdapter(val models: ArrayList<IntroDeveloperModel>): RecyclerView.Adapter<IntroDeveloperAdapter.IntroDeveloperViewHolder>(){

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): IntroDeveloperViewHolder {
        val view = LayoutInflater.from(p0.context).inflate(R.layout.item_intro_developer,p0, false)
        return IntroDeveloperViewHolder(view)
    }

    override fun getItemCount() = models.size

    override fun onBindViewHolder(p0: IntroDeveloperViewHolder, p1: Int) = p0.bind(models[p1])

    class IntroDeveloperViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val name = itemView.find<TextView>(R.id.introDeveloperItem_name_tv)
        val career = itemView.find<TextView>(R.id.introDeveloperItem_career_tv)
        val image = itemView.find<ImageView>(R.id.introDeveloperItem_image_iv)
        fun bind(model: IntroDeveloperModel){
            name.text = model.name
            career.text = model.career
//            image.image =
        }
    }
}