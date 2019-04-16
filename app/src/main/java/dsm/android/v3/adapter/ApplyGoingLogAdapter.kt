package dsm.android.v3.adapter

import android.arch.lifecycle.MutableLiveData
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import dsm.android.v3.BR
import dsm.android.v3.databinding.ItemApplyGoingLogBinding
import dsm.android.v3.model.ApplyGoingModel

class ApplyGoingLogAdapter :
    RecyclerView.Adapter<ApplyGoingLogAdapter.ApplyGoingLogViewHolder>() {

    private var logItems = ArrayList<ApplyGoingModel.ApplyGoingDataModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ApplyGoingLogViewHolder {
        val binding = ItemApplyGoingLogBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ApplyGoingLogViewHolder(binding)
    }

    override fun getItemCount(): Int = logItems.size

    override fun onBindViewHolder(holder: ApplyGoingLogViewHolder, position: Int) = holder.bind(logItems[position])

    fun setItem(logItems: MutableLiveData<ArrayList<ApplyGoingModel.ApplyGoingDataModel>>) {
        this.logItems = logItems.value!!
        notifyDataSetChanged()
    }

    inner class ApplyGoingLogViewHolder(val binding: ItemApplyGoingLogBinding) : RecyclerView.ViewHolder(binding.root.rootView) {

        fun bind(log: ApplyGoingModel.ApplyGoingDataModel) {
            binding.setVariable(BR.goingLog, log)
        }
    }
}