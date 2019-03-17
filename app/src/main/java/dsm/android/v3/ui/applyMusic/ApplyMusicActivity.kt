package dsm.android.v3.ui.applyMusic

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v4.view.PagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import dsm.android.v3.R
import dsm.android.v3.databinding.ActivityApplyMusicBinding
import dsm.android.v3.model.ApplyPagerModel
import dsm.android.v3.util.DataBindingActivity
import dsm.android.v3.util.LifecycleCallback
import kotlinx.android.synthetic.main.activity_apply_music.*
import kotlinx.android.synthetic.main.item_apply_pager.view.*
import org.jetbrains.anko.find
import org.jetbrains.anko.textColor

class ApplyMusicActivity: DataBindingActivity<ActivityApplyMusicBinding>(), ApplyMusicContract {
    override val layoutId: Int
        get() = R.layout.activity_apply_music

    override lateinit var viewGroup: ViewGroup

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(apply_music_toolbar)

        val factory = ApplyMusicViewModelFactory(this)
        binding.applyMusicViewModel = ViewModelProviders.of( this, factory).get(ApplyMusicViewModel::class.java)
        register(binding.applyMusicViewModel!!)
    }

    override fun closeApplyMusic() = finish()

    override fun setViewPager(mondayCount: Int, tuesdayCount: Int, wednesdayCount: Int, thursdayCount: Int, fridayCount: Int, saturdayCount: Int, sunday: Int){
        val models = arrayListOf(
            ApplyPagerModel(getString(R.string.apply_music_monday_title), getString(R.string.apply_music_monday_explanation), mondayCount),
            ApplyPagerModel(getString(R.string.apply_music_tuesday_title), getString(R.string.apply_music_tuesday_explanation), tuesdayCount),
            ApplyPagerModel(getString(R.string.apply_music_wednesday_title), getString(R.string.apply_music_wednesday_explanation), wednesdayCount),
            ApplyPagerModel(getString(R.string.apply_music_thursday_title), getString(R.string.apply_music_thursday_explanation), thursdayCount),
            ApplyPagerModel(getString(R.string.apply_music_friday_title), getString(R.string.apply_music_friday_explanation), fridayCount)
        )
        apply_music_apply_list_pager.adapter = ApplyPageAdapter(models)
    }

    override fun changeColor(view: View){
        view.item_applyGoing_card.setCardBackgroundColor(ContextCompat.getColor(this, R.color.colorPrimary))
        view.item_applyGoing_title_tv.textColor = ContextCompat.getColor(this, R.color.colorWhite)
        view.item_applyGoing_explanation_tv.textColor = ContextCompat.getColor(this, R.color.colorWhite)
    }

    override fun originalColor(view: View){
        view.item_applyGoing_card.setCardBackgroundColor(ContextCompat.getColor(this, R.color.colorWhite))
        view.item_applyGoing_title_tv.textColor = ContextCompat.getColor(this, R.color.colorPrimary)
        view.item_applyGoing_explanation_tv.textColor = ContextCompat.getColor(this, R.color.colorGray600)
    }

    inner class ApplyPageAdapter(val models: ArrayList<ApplyPagerModel>): PagerAdapter(){

        override fun isViewFromObject(p0: View, p1: Any): Boolean = p0 == p1

        override fun getCount(): Int = models.size

        override fun destroyItem(container: ViewGroup, position: Int, any: Any) = container.removeView(any as View)

        override fun instantiateItem(container: ViewGroup, position: Int): Any {
            val layoutInflater = LayoutInflater.from(baseContext)
            val view = layoutInflater.inflate(R.layout.item_apply_pager, container, false)
            view.find<TextView>(R.id.item_applyGoing_title_tv).text = models[position].week
            view.find<TextView>(R.id.item_applyGoing_explanation_tv).text = models[position].description
            view.find<TextView>(R.id.item_applyGoing_count_tv).text = models[position].cnt.toString()
            container.addView(view)
            return view
        }

        override fun startUpdate(container: ViewGroup) {
            super.startUpdate(container)
            viewGroup = container
        }
    }

}