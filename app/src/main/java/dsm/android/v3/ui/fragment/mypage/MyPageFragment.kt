package dsm.android.v3.ui.fragment.mypage

import android.animation.ValueAnimator
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.FragmentManager
import android.view.View
import dsm.android.v3.util.DataBindingFragment
import android.view.LayoutInflater
import android.view.ViewGroup
import dsm.android.v3.data.local.dao.AuthDao
import dsm.android.v3.data.local.shared.LocalStorage
import dsm.android.v3.data.remote.ApiClient
import dsm.android.v3.databinding.FragmentMypageBinding
import dsm.android.v3.domain.repository.mypage.MyPageRepositoryImpl
import dsm.android.v3.presentation.viewModel.mypage.MyPageViewModel
import dsm.android.v3.presentation.viewModel.mypage.MyPageViewModelFactory
import dsm.android.v3.ui.dialogFragment.bugReportDialog.BugReportDialogFragment
import dsm.android.v3.ui.activity.changePassword.ChangePasswordActivity
import dsm.android.v3.ui.dialogFragment.institutionReportDialog.InstitutionDialogFragment
import dsm.android.v3.ui.activity.introduceTeam.IntroDeveloperActivity
import dsm.android.v3.ui.dialogFragment.logOutDialog.LogoutDialogFragment
import dsm.android.v3.ui.activity.pointLog.PointLogActivity
import org.jetbrains.anko.support.v4.startActivity
import org.jetbrains.anko.support.v4.toast
import javax.inject.Inject


class MyPageFragment:DataBindingFragment<FragmentMypageBinding>() {

    override val layoutId: Int
        get() = dsm.android.v3.R.layout.fragment_mypage

    @Inject
    lateinit var apiClient: ApiClient

    @Inject
    lateinit var localStorage: LocalStorage

    @Inject
    lateinit var authDao: AuthDao

    val factory by lazy { MyPageViewModelFactory(MyPageRepositoryImpl(apiClient, localStorage, authDao)) }

    private val fm: FragmentManager? by lazy { fragmentManager }
    private val logoutDialogFragment: LogoutDialogFragment by  lazy { LogoutDialogFragment() }
    private val bugReportDialogFragment: BugReportDialogFragment by lazy { BugReportDialogFragment() }
    private val institutionDialogFragment: InstitutionDialogFragment by lazy { InstitutionDialogFragment() }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val viewModel = ViewModelProviders.of(this, factory).get(MyPageViewModel::class.java)

        viewModel.pointCountAnimatorEvent.observe(this, Observer {
            startCountAnimation(binding.myPageViewModel!!.goodPoint.value!!, binding.myPageViewModel!!.badPoint.value!!)
        })
        viewModel.intentIntroDevelopersEvent.observe(this, Observer { startActivity<IntroDeveloperActivity>() })
        viewModel.intentMeritHistoryEvent.observe(this, Observer { startActivity<PointLogActivity>() })
        viewModel.intentPasswordChangeEvent.observe(this, Observer { startActivity<ChangePasswordActivity>() })
        viewModel.intentQuestionResearchEvent.observe(this, Observer { toast("오픈 준비 중입니다.") })
        viewModel.showBugReportEvent.observe(this, Observer { bugReportDialogFragment.show(fm, "bug") })
        viewModel.showInstitutionReportEvent.observe(this, Observer { institutionDialogFragment.show(fm, "institution") })
        viewModel.showLogoutEvent.observe(this, Observer { logoutDialogFragment.show(fm, "logout") })

        binding.myPageViewModel = viewModel
        register(binding.myPageViewModel!!)
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
