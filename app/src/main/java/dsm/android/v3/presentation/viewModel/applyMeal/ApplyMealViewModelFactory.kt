package dsm.android.v3.presentation.viewModel.applyMeal

import android.app.Application
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import dsm.android.v3.domain.repository.applyMeal.ApplyMealRepository

class ApplyMealViewModelFactory(private val applyMealRepository: ApplyMealRepository,val application:Application):ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        modelClass.getConstructor(ApplyMealRepository::class.java,Application::class.java).newInstance(applyMealRepository,application)
}