package dsm.android.v3.ui.mypage

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.os.SystemClock
import android.view.View
import dsm.android.v3.util.DataBindingFragment
import android.view.LayoutInflater
import android.view.ViewGroup
import dsm.android.v3.databinding.FragmentMypageBinding


class MyPageFragment() :DataBindingFragment<FragmentMypageBinding>(), MyPageContract {
    override val layoutId: Int
        get() = dsm.android.v3.R.layout.fragment_mypage

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val factory = MyPageViewModelFactory(this)
        binding.myPageViewModel = ViewModelProviders.of(this, factory).get(MyPageViewModel::class.java)
        return rootView
    }

    override fun showDialogInstitutionReport() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showDialogBugReport() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showDialogLogout() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun intentQuestionResearch() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun intentPasswordChange() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun intentMeriteHistory() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun intentintroDevelopers() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}