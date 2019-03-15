package dsm.android.v3.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.ScaleAnimation
import dsm.android.v3.R
import dsm.android.v3.model.NoticeListModel
import kotlinx.android.synthetic.main.item_notice.view.*

class NoticeRVAdapter(val context : Context, var data : ArrayList<NoticeListModel>) : RecyclerView.Adapter<NoticeViewHolder>() {

    lateinit var viewListener : View.OnClickListener

    override fun getItemCount() = data.size

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): NoticeViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_notice, parent, false)
        return NoticeViewHolder(view)
    }

    override fun onBindViewHolder(viewHodler: NoticeViewHolder, position: Int) {
        viewHodler.view.setOnClickListener(viewListener)
        viewHodler.setData(data!![position])
        setScaleAnimation(viewHodler.view)
    }

    private fun setScaleAnimation(view: View) {
        val anim =
            ScaleAnimation(0.0f, 1.0f, 0.0f, 1.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f)
        anim.duration = 300
        view.startAnimation(anim)
    }

    fun setClickListener (listener : View.OnClickListener){
        viewListener = listener
    }
}

class NoticeViewHolder(val view : View) : RecyclerView.ViewHolder(view) {

    fun setData(data : NoticeListModel) {
        with(view) {
            itemNotice_title_tv.text = data.title
            itemNotice_date_tv.text = data.date
        }
    }
}