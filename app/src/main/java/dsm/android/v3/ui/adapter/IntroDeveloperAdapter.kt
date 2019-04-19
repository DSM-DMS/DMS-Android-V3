package dsm.android.v3.ui.adapter

import android.support.v7.widget.RecyclerView
import android.util.Base64
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import dsm.android.v3.R
import dsm.android.v3.presentation.model.IntroDeveloperModel
import org.jetbrains.anko.find

class IntroDeveloperAdapter(val models: ArrayList<IntroDeveloperModel>) :
    RecyclerView.Adapter<IntroDeveloperAdapter.IntroDeveloperViewHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): IntroDeveloperViewHolder {
        val view = LayoutInflater.from(p0.context).inflate(R.layout.item_intro_developer, p0, false)
        return IntroDeveloperViewHolder(view)
    }

    override fun getItemCount() = models.size

    override fun onBindViewHolder(p0: IntroDeveloperViewHolder, p1: Int) = p0.bind(models[p1])

    class IntroDeveloperViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name = itemView.find<TextView>(R.id.introDeveloperItem_name_tv)
        val career = itemView.find<TextView>(R.id.introDeveloperItem_career_tv)
        val image = itemView.find<ImageView>(R.id.introDeveloperItem_image_iv)
        fun bind(model: IntroDeveloperModel) {
            val base64Name = Base64.encodeToString(model.name.toByteArray(), 0)
                .replace("\n", "").replace("+", "%2B")
            name.text = model.name
            career.text = model.career
            Glide.with(image)
                .load("https://s3.ap-northeast-2.amazonaws.com/dms-developers/$base64Name.png")
                .into(image)
        }
    }
}