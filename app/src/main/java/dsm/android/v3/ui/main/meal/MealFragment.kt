package dsm.android.v3.ui.main.meal


import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import dsm.android.v3.R
import dsm.android.v3.adapter.MealPagerAdapter
import dsm.android.v3.databinding.FragmentMealBinding
import dsm.android.v3.model.MealModel
import dsm.android.v3.util.DataBindingFragment
import org.jetbrains.anko.find
import org.jetbrains.anko.support.v4.onPageChangeListener
import org.jetbrains.anko.support.v4.toast

class MealFragment : DataBindingFragment<FragmentMealBinding>() {

    override val layoutId: Int
        get() = R.layout.fragment_meal

    val viewModel by lazy { ViewModelProviders.of(this)[MealFragmentViewModel::class.java] }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding.vm = viewModel

        viewModel.getMeal()

        viewModel.pageStatusLiveData.observe(this, Observer {
            toast("$it")
        })

        viewModel.meals.observe(this, Observer {
            rootView.find<ViewPager>(R.id.mealFragment_meal_vp).adapter = MealPagerAdapter(it!!)
        })

        return rootView
    }


}
