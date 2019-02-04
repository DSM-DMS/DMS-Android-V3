package dsm.android.v3.ui.mypage

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.os.SystemClock
import android.support.v4.app.FragmentManager
import android.view.View
import dsm.android.v3.util.DataBindingFragment
import android.view.LayoutInflater
import android.view.ViewGroup
import dsm.android.v3.databinding.FragmentMypageBinding
import android.databinding.adapters.TextViewBindingAdapter.setText
import android.animation.ValueAnimator
import kotlinx.android.synthetic.main.fragment_mypage.*


class MyPageFragment() :DataBindingFragment<FragmentMypageBinding>(), MyPageContract {
    override val layoutId: Int
        get() = dsm.android.v3.R.layout.fragment_mypage

    private val fm: FragmentManager? by lazy { fragmentManager }
    private val logoutDialogFragment:  LogoutDialogFragmenet by  lazy { LogoutDialogFragmenet() }
    private val bugReportDialogFragment: BugReportDialogFragment by lazy { BugReportDialogFragment() }
    private val institutionDialogFragment: InstitutionDialogFragment by lazy { InstitutionDialogFragment() }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val factory = MyPageViewModelFactory(this)
        binding.myPageViewModel = ViewModelProviders.of(this, factory).get(MyPageViewModel::class.java)
        return rootView
    }

    override fun startCountAnimation(merit: Int, demerit: Int) {

        val meritAnimator = ValueAnimator.ofInt(0, merit)
        val demeritAnimator = ValueAnimator.ofInt(0, demerit)

        meritAnimator.duration = 1000
        demeritAnimator.duration = 1000

        meritAnimator.addUpdateListener { animation -> mypage_merit_tv.setText(animation.animatedValue.toString()) }
        demeritAnimator.addUpdateListener { animation -> mypage_demerit_tv.setText(animation.animatedValue.toString()) }

        meritAnimator.start()
        demeritAnimator.start()
    }

    override fun showDialogInstitutionReport() = institutionDialogFragment.show(fm, "institution")

    override fun showDialogBugReport() = bugReportDialogFragment.show(fm, "bug")

    override fun showDialogLogout() = logoutDialogFragment.show(fm, "logout")

    override fun intentQuestionResearch() {
        // startActivity<>() 설문 조사 액티비티로 전환
    }

    override fun intentPasswordChange() {
        // startActivity<>() 비밀번호 전환 액티비티로 전환
    }

    override fun intentMeriteHistory() {
        // startActivity<>() 상벌점 내역 액티비티로 전환
    }

    override fun intentintroDevelopers() {
        // startActivity<>() 개발자 소개하는 액티비티로 전환
    }
}