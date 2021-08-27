package dsm.android.v3.ui.activity.applyMeal

import android.os.Bundle
import android.os.PersistableBundle
import dagger.android.support.DaggerAppCompatActivity
import dsm.android.v3.R
import kotlinx.android.synthetic.main.activity_apply_meal.*

class ApplyMealActivity:DaggerAppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(R.layout.activity_apply_meal)
        supportActionBar?.setDisplayHomeAsUpEnabled(true);
        supportActionBar?.setDisplayShowHomeEnabled(true);
        title = "주말급식 신청"
        apply_meal_toolbar.setNavigationOnClickListener {
            onBackPressed()
        }
    }
}