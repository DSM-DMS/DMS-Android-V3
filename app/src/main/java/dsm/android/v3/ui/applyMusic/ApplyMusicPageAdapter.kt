package dsm.android.v3.ui.applyMusic

import android.arch.lifecycle.MutableLiveData
import android.support.v4.view.PagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dsm.android.v3.BR
import dsm.android.v3.databinding.ItemApplyMusicPagerBinding
import dsm.android.v3.model.ApplyPagerModel

class ApplyMusicPageAdapter(val viewModel: ApplyMusicViewModel) : PagerAdapter() {

    private var applyPagerModels = ArrayList<ApplyPagerModel>()

    override fun isViewFromObject(p0: View, p1: Any): Boolean = p0 == p1

    override fun getCount(): Int = applyPagerModels.size

    override fun destroyItem(container: ViewGroup, position: Int, any: Any) = container.removeView(any as View)

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val binding = ItemApplyMusicPagerBinding.inflate(LayoutInflater.from(container.context), container, false)
        binding.setVariable(BR.applyPagerModel, applyPagerModels[position])
        binding.applyMusicViewModel = viewModel

        container.addView(binding.root)
        return binding.root
    }

    fun setItem(applyPagerModels: MutableLiveData<ArrayList<ApplyPagerModel>>) {
        if(applyPagerModels.value != null) {
            this.applyPagerModels = applyPagerModels.value!!
            notifyDataSetChanged()
        }
    }
}