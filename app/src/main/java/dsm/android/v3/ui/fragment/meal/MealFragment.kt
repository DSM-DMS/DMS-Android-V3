package dsm.android.v3.ui.fragment.meal

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dsm.android.v3.R
import dsm.android.v3.data.remote.ApiClient
import dsm.android.v3.ui.adapter.MealPagerAdapter
import dsm.android.v3.databinding.FragmentMealBinding
import dsm.android.v3.domain.repository.meal.MealRepositoryImpl
import dsm.android.v3.presentation.viewModel.meal.MealFragmentViewModel
import dsm.android.v3.presentation.viewModel.meal.MealViewModelFactory
import dsm.android.v3.util.DataBindingFragment
import org.jetbrains.anko.find
import javax.inject.Inject

class MealFragment : DataBindingFragment<FragmentMealBinding>() {

    override val layoutId: Int
        get() = R.layout.fragment_meal

    @Inject
    lateinit var apiClient: ApiClient

    val factory by lazy { MealViewModelFactory(MealRepositoryImpl(apiClient)) }
    val viewModel by lazy { ViewModelProviders.of(this, factory)[MealFragmentViewModel::class.java] }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding.vm = viewModel

        viewModel.getMeal()

        viewModel.meals.observe(this, Observer {
            rootView.find<ViewPager>(R.id.mealFragment_meal_vp).adapter = MealPagerAdapter(it!!)
        })

        return rootView
    }


}
