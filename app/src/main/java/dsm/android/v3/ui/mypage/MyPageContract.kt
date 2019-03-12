package dsm.android.v3.ui.mypage

interface MyPageContract{

    fun startCountAnimation(merit: Int, demerit: Int)

    fun showDialogInstitutionReport()
    fun showDialogBugReport()
    fun showDialogLogout()

    fun intentQuestionResearch()
    fun intentPasswordChange()
    fun intentMeriteHistory()
    fun intentintroDevelopers()
}