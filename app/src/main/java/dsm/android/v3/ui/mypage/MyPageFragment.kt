package dsm.android.v3.ui.mypage

import android.animation.ValueAnimator
import android.app.Application
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.FragmentManager
import android.view.View
import dsm.android.v3.util.DataBindingFragment
import android.view.LayoutInflater
import android.view.ViewGroup
import dsm.android.v3.R
import dsm.android.v3.databinding.FragmentMypageBinding
import dsm.android.v3.ui.bugReportDialog.BugReportDialogFragment
import dsm.android.v3.ui.changePassword.ChangePasswordActivity
import dsm.android.v3.ui.institutionReportDialog.InstitutionDialogFragment
import dsm.android.v3.ui.introduceTeam.IntroDeveloperActivity
import dsm.android.v3.ui.logOutDialog.LogOutDialogFragment
import dsm.android.v3.ui.pointLog.PointLogActivity
import kotlinx.android.synthetic.main.dialog_logout.*
import kotlinx.android.synthetic.main.fragment_mypage.*
import org.jetbrains.anko.support.v4.startActivity
import org.jetbrains.anko.support.v4.toast


class MyPageFragment:DataBindingFragment<FragmentMypageBinding>() {

    override val layoutId: Int
        get() = dsm.android.v3.R.layout.fragment_mypage

    private val fm: FragmentManager? by lazy { fragmentManager }
    private val logoutDialogFragment: LogOutDialogFragment by  lazy { LogOutDialogFragment() }
    private val bugReportDialogFragment: BugReportDialogFragment by lazy { BugReportDialogFragment() }
    private val institutionDialogFragment: InstitutionDialogFragment by lazy { InstitutionDialogFragment() }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding.myPageViewModel = ViewModelProviders.of(this).get(MyPageViewModel::class.java)
        register(binding.myPageViewModel!!)

        binding.myPageViewModel!!.pointCountAnimatorEvent.observe(this, Observer {
            startCountAnimation(binding.myPageViewModel!!.goodPoint.value!!, binding.myPageViewModel!!.badPoint.value!!)
        })

        binding.myPageViewModel!!.intentIntroDevelopersEvent.observe(this, Observer {
            startActivity<IntroDeveloperActivity>()
        })

        binding.myPageViewModel!!.intentMeritHistoryEvent.observe(this, Observer {
            startActivity<PointLogActivity>()
        })

        binding.myPageViewModel!!.intentPasswordChangeEvent.observe(this, Observer {
            startActivity<ChangePasswordActivity>()
        })

        binding.myPageViewModel!!.intentQuestionResearchEvent.observe(this, Observer {
            toast("오픈 준비 중입니다.")
        })

        binding.myPageViewModel!!.showBugReportEvent.observe(this, Observer {
            bugReportDialogFragment.show(fm, "bug")
        })

        binding.myPageViewModel!!.showInstitutionReportEvent.observe(this, Observer {
            institutionDialogFragment.show(fm, "institution")
        })

        binding.myPageViewModel!!.showLogoutEvent.observe(this, Observer {
            logoutDialogFragment.show(fm, "logout")
        })

        return rootView
    }

    private fun startCountAnimation(merit: Int, demerit: Int) {
        val meritAnimator = ValueAnimator.ofInt(0, merit)
        val demeritAnimator = ValueAnimator.ofInt(0, demerit)
        meritAnimator.duration = 1000
        demeritAnimator.duration = 1000

        meritAnimator.addUpdateListener { animation -> binding.myPageViewModel!!.goodPointText.value = animation.animatedValue.toString() }
        demeritAnimator.addUpdateListener { animation -> binding.myPageViewModel!!.badPointText.value = animation.animatedValue.toString() }
        meritAnimator.start()
        demeritAnimator.start()
    }
}
