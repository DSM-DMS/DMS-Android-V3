package dsm.android.v3.adapter.Notice

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.ScaleAnimation
import dsm.android.v3.R
import dsm.android.v3.model.Notice.NoticeListModel
import dsm.android.v3.model.Notice.NoticeModel
import dsm.android.v3.ui.notice.NoticeActivity
import kotlinx.android.synthetic.main.item_notice.view.*

class NoticeRVAdapter(val context : Context, var data : NoticeListModel, var activity : NoticeActivity) : RecyclerView.Adapter<NoticeRVAdapter.NoticeViewHolder>() {

    override fun getItemCount() = data.list.size

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): NoticeViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_notice, parent, false)
        return NoticeViewHolder(view, activity, data)
    }

    override fun onBindViewHolder(viewHodler: NoticeViewHolder, position: Int) {
        viewHodler.setData(data!!.list[position])
        setScaleAnimation(viewHodler.view)
    }

    private fun setScaleAnimation(view: View) {
        val anim =
            ScaleAnimation(0.0f, 1.0f, 0.0f, 1.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f)
        anim.duration = 300
        view.startAnimation(anim)
    }

    class NoticeViewHolder(val view : View, val activity: NoticeActivity, val data : NoticeListModel) : RecyclerView.ViewHolder(view), View.OnClickListener{

        init {
            view.setOnClickListener(this)
        }

        fun setData(data : NoticeModel) {
            with(view) {
                itemNotice_title_tv.text = data.title
                itemNotice_date_tv.text = frameDate(data.postDate)
            }
        }

        override fun onClick(v: View?) {
            var position = layoutPosition
            activity.createDescription(data.list[position].id, true)
        }

        fun frameDate(date : String) :String {
            var frameDate = date.substring(0, 10)
            return frameDate
        }
    }
}