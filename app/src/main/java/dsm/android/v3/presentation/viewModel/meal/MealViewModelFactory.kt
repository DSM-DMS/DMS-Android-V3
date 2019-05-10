package dsm.android.v3.presentation.viewModel.meal

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import dsm.android.v3.domain.repository.meal.MealRepository

class MealViewModelFactory(val mealRepository: MealRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
            modelClass.getConstructor(MealRepository::class.java).newInstance(mealRepository)
}