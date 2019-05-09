package dsm.android.v3.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import dsm.android.v3.databinding.ItemApplymusicLogBinding
import dsm.android.v3.domain.entity.ApplyMusicDetailModel
import dsm.android.v3.presentation.viewModel.applyMusic.ApplyMusicViewModel

class ApplyMusicAdapter(val viewModel: ApplyMusicViewModel) :
    RecyclerView.Adapter<ApplyMusicAdapter.ApplyMusicViewHolder>() {
    var item = arrayListOf<ApplyMusicDetailModel>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(view: ViewGroup, p1: Int): ApplyMusicViewHolder {
        val binding = ItemApplymusicLogBinding.inflate(LayoutInflater.from(view.context), view, false)
        return ApplyMusicViewHolder(binding)
    }

    override fun getItemCount(): Int = item.size

    override fun onBindViewHolder(viewHolder: ApplyMusicViewHolder, p1: Int) {
        viewHolder.bind()
    }

    inner class ApplyMusicViewHolder(val binding: ItemApplymusicLogBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind() {
            binding.musicData = item[adapterPosition]
            binding.viewModel = viewModel
            binding.index = adapterPosition
        }

    }
}