package dsm.android.v3.adapter.notice

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.ScaleAnimation
import dsm.android.v3.databinding.ItemNoticeBinding
import dsm.android.v3.model.notice.NoticeListModel
import dsm.android.v3.ui.notice.NoticeViewModel

class NoticeAdapter(val viewModel: NoticeViewModel) : RecyclerView.Adapter<NoticeAdapter.NoticeViewHolder>() {
    var item: NoticeListModel = NoticeListModel()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): NoticeViewHolder {
        val binding = ItemNoticeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NoticeViewHolder(binding)
    }

    override fun getItemCount() = item.list.size

    override fun onBindViewHolder(viewHodler: NoticeViewHolder, position: Int) {
        viewHodler.bind()
        setScaleAnimation(viewHodler.binding.root)
    }

    private fun setScaleAnimation(view: View) {
        val anim =
            ScaleAnimation(0.0f, 1.0f, 0.0f, 1.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f)
        anim.duration = 300
        view.startAnimation(anim)
    }

    inner class NoticeViewHolder(val binding: ItemNoticeBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind() {
            binding.vm = viewModel
            binding.noticeData = viewModel.noticeListLiveData.value!!.list[adapterPosition]
        }

        fun frameDate(date: String) = date.substring(0, 10)
    }
}