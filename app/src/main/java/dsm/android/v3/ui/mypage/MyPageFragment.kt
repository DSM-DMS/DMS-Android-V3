package dsm.android.v3.ui.mypage

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.FragmentManager
import android.view.View
import dsm.android.v3.util.DataBindingFragment
import android.view.LayoutInflater
import android.view.ViewGroup
import dsm.android.v3.databinding.FragmentMypageBinding
import android.animation.ValueAnimator
import dsm.android.v3.ui.bugReportDialog.BugReportDialogFragment
import dsm.android.v3.ui.changePassword.ChangePasswordActivity
import dsm.android.v3.ui.institutionReportDialog.InstitutionDialogFragment
import dsm.android.v3.ui.logOutDialog.LogoutDialogFragment
import dsm.android.v3.ui.pointLog.PointLogActivity
import kotlinx.android.synthetic.main.fragment_mypage.*
import org.jetbrains.anko.support.v4.startActivity
import org.jetbrains.anko.support.v4.toast


class MyPageFragment:DataBindingFragment<FragmentMypageBinding>(), MyPageContract {

    override val layoutId: Int
        get() = dsm.android.v3.R.layout.fragment_mypage

    private val fm: FragmentManager? by lazy { fragmentManager }
    private val logoutDialogFragment: LogoutDialogFragment by  lazy { LogoutDialogFragment() }
    private val bugReportDialogFragment: BugReportDialogFragment by lazy { BugReportDialogFragment() }
    private val institutionDialogFragment: InstitutionDialogFragment by lazy { InstitutionDialogFragment() }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val factory = MyPageViewModelFactory(this, context!!)
        binding.myPageViewModel = ViewModelProviders.of(this, factory).get(MyPageViewModel::class.java)
        register(binding.myPageViewModel!!)
        return rootView
    }

    override fun startCountAnimation(merit: Int, demerit: Int) {

        val meritAnimator = ValueAnimator.ofInt(0, merit)
        val demeritAnimator = ValueAnimator.ofInt(0, demerit)

        meritAnimator.duration = 500
        demeritAnimator.duration = 500

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
         startActivity<ChangePasswordActivity>() // 비밀번호 전환 액티비티로 전환
    }

    override fun intentMeriteHistory() {
         startActivity<PointLogActivity>()
    }

    override fun intentintroDevelopers() {
        // startActivity<>() 개발자 소개하는 액티비티로 전환
    }
}
