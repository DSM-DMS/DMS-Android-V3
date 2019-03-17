package dsm.android.v3.ui.main

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import dsm.android.v3.R
import dsm.android.v3.ui.main.meal.MealFragment
import dsm.android.v3.ui.main.putIn.PutInFragment
import dsm.android.v3.ui.mypage.MyPageFragment
import dsm.android.v3.util.saveToken
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private val navigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        val transaction = supportFragmentManager.beginTransaction()
        when (item.itemId) {
            R.id.navigation_food -> {
                transaction.replace(R.id.main_container, MealFragment())
                transaction.commit()
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_submit -> {
                transaction.replace(R.id.main_container, PutInFragment())
                transaction.commit()
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notice -> {
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_myPage -> {
                transaction.replace(R.id.main_container, MyPageFragment())
                transaction.commit()
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction().run {
            replace(R.id.main_container, MealFragment())
            commit()
        }
        navigation.setOnNavigationItemSelectedListener(navigationItemSelectedListener)
    }

    override fun onBackPressed() {
        if (navigation.selectedItemId == R.id.navigation_food)
            super.onBackPressed()
        else
            navigation.selectedItemId = R.id.navigation_food
    }
}
