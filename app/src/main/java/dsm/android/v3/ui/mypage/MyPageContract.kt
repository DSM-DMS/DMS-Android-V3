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

    interface LogoutContract{
        fun exitLogout()
    }

    interface InstitutionReportContract{
        fun exitInstitutionReport()
        fun flagInstitutionTitleBlankError()
        fun flagInstitutionRoomNumberBlankError()
        fun flagInstitutionContentBlankError()
    }

    interface BugReportContract{
        fun exitBugReport()
        fun flagBugTitleBlankError()
        fun flagBugContentBlankError()
    }
}