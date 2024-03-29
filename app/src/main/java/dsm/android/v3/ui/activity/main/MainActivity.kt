package dsm.android.v3.ui.activity.main

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatDelegate
import dagger.android.support.DaggerAppCompatActivity
import dsm.android.v3.R
import dsm.android.v3.data.local.shared.LocalStorage
import dsm.android.v3.ui.activity.setting.SettingActivity
import dsm.android.v3.ui.fragment.meal.MealFragment
import dsm.android.v3.ui.fragment.putIn.PutInFragment
import dsm.android.v3.ui.fragment.mypage.MyPageFragment
import dsm.android.v3.ui.fragment.notice.NoticeFragment
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.Subject
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.toast
import javax.inject.Inject


class MainActivity : DaggerAppCompatActivity() {
    private val backButtonSubject: Subject<Long> =
        BehaviorSubject.createDefault(0L)
            .toSerialized()

    private val backButtonSubjectDisposable: Disposable = backButtonSubject
        .buffer(2, 1)
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe {
            if (it[1] - it[0] <= 1500) finishAffinity()
            else toast("뒤로가기 버튼을 한 번 더 누르시면 종료됩니다.")
        }

    override fun onBackPressed() {
        if (navigation.selectedItemId == R.id.navigation_food)
            backButtonSubject.onNext(System.currentTimeMillis())
        else navigation.selectedItemId = R.id.navigation_food
    }

    private val navigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
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
                    transaction.replace(R.id.main_container, NoticeFragment())
                    transaction.commit()
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
        setBottomNavigation()
    }

    fun startSettingActivity() {
        val intent = Intent(this, SettingActivity::class.java)
        startActivityForResult(intent, 0)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 0) {
            setBottomNavigation()
        }
    }

    private fun setBottomNavigation() {
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction().run {
            replace(R.id.main_container, MealFragment())
            commit()
        }
        navigation.setOnNavigationItemSelectedListener(navigationItemSelectedListener)
        navigation.selectedItemId = R.id.navigation_food
    }

    override fun onDestroy() {
        super.onDestroy()
        backButtonSubjectDisposable.dispose()
    }
}
