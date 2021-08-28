package dsm.android.v3.presentation.viewModel.applyMeal

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import dsm.android.v3.domain.repository.applyMeal.ApplyMealRepository

class ApplyMealViewModelFactory(private val applyMealRepository: ApplyMealRepository):ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        modelClass.getConstructor(ApplyMealRepository::class.java).newInstance(applyMealRepository)
}