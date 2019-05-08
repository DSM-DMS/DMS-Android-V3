package dsm.android.v3.ui.fragment.applyMusic


import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.view.PagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import dsm.android.v3.R
import dsm.android.v3.databinding.FragmentApplyMusicBinding
import dsm.android.v3.presentation.di.app.BaseApp
import dsm.android.v3.presentation.model.ApplyPagerModel
import dsm.android.v3.presentation.viewModel.applyMusic.ApplyMusicViewModel
import dsm.android.v3.util.DataBindingFragment
import kotlinx.android.synthetic.main.fragment_apply_music.*
import org.jetbrains.anko.find

class ApplyMusicFragment : DataBindingFragment<FragmentApplyMusicBinding>() {

    override val layoutId: Int
        get() = R.layout.fragment_apply_music

    override fun inject() = BaseApp.appComponent.injectFragment(this)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewModel = ViewModelProviders.of(activity!!).get(ApplyMusicViewModel::class.java)
        binding.viewModel = viewModel
        register(binding.viewModel!!)
        viewModel.actionMusicLogLiveEvent.observe(this, Observer {
            findNavController().navigate(ApplyMusicFragmentDirections.actionApplyMusicFragmentToApplyMusicLogFragment())
        })

        viewModel.model.observe(this, Observer {
            setViewPager(it!!.mon.size, it.tue.size, it.wed.size, it.thu.size, it.fri.size)
        })

    }

    fun setViewPager(monday: Int, tuesday: Int, wednesday: Int, thursday: Int, friday: Int) {
        val models = arrayListOf(
            ApplyPagerModel(
                getString(R.string.apply_music_monday_title),
                getString(R.string.apply_music_monday_explanation),
                monday
            ),
            ApplyPagerModel(
                getString(R.string.apply_music_tuesday_title),
                getString(R.string.apply_music_tuesday_explanation),
                tuesday
            ),
            ApplyPagerModel(
                getString(R.string.apply_music_wednesday_title),
                getString(R.string.apply_music_wednesday_explanation),
                wednesday
            ),
            ApplyPagerModel(
                getString(R.string.apply_music_thursday_title),
                getString(R.string.apply_music_thursday_explanation),
                thursday
            ),
            ApplyPagerModel(
                getString(R.string.apply_music_friday_title),
                getString(R.string.apply_music_friday_explanation),
                friday
            )
        )
        apply_music_apply_list_pager.adapter = ApplyPageAdapter(models)
    }

    inner class ApplyPageAdapter(val models: ArrayList<ApplyPagerModel>) : PagerAdapter() {

        override fun isViewFromObject(p0: View, p1: Any): Boolean = p0 == p1

        override fun getCount(): Int = models.size

        override fun destroyItem(container: ViewGroup, position: Int, any: Any) = container.removeView(any as View)

        override fun instantiateItem(container: ViewGroup, position: Int): Any {
            val layoutInflater = LayoutInflater.from(context)
            val view = layoutInflater.inflate(R.layout.item_apply_pager, container, false)
            view.find<TextView>(R.id.item_applyGoing_title_tv).text = models[position].week
            view.find<TextView>(R.id.item_applyGoing_explanation_tv).text = models[position].description
            view.find<TextView>(R.id.item_applyGoing_count_tv).text = models[position].cnt.toString()
            container.addView(view)
            return view
        }

        override fun startUpdate(container: ViewGroup) {
            super.startUpdate(container)
        }
    }
}
