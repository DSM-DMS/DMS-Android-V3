package dsm.android.v3.ui.mypage

import android.app.Application
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


class MyPageFragment:DataBindingFragment<FragmentMypageBinding>(), MyPageContract {

    override val layoutId: Int
        get() = dsm.android.v3.R.layout.fragment_mypage

    private val fm: FragmentManager? by lazy { fragmentManager }
    private val logoutDialogFragment: LogOutDialogFragment by  lazy { LogOutDialogFragment() }
    private val bugReportDialogFragment: BugReportDialogFragment by lazy { BugReportDialogFragment() }
    private val institutionDialogFragment: InstitutionDialogFragment by lazy { InstitutionDialogFragment() }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val factory = MyPageViewModelFactory(this, activity!!.application)
        binding.myPageViewModel = ViewModelProviders.of(this, factory).get(MyPageViewModel::class.java)
        register(binding.myPageViewModel!!)
        return rootView
    }

    override fun showDialogInstitutionReport() = institutionDialogFragment.show(fm, "institution")

    override fun showDialogBugReport() = bugReportDialogFragment.show(fm, "bug")

    override fun showDialogLogout() = logoutDialogFragment.show(fm, "logout")

    override fun intentQuestionResearch() = toast("오픈 준비 중입니다.").show()

    override fun intentPasswordChange() = startActivity<ChangePasswordActivity>()

    override fun intentMeriteHistory() = startActivity<PointLogActivity>()

    override fun intentintroDevelopers() = startActivity<IntroDeveloperActivity>()
}
