package dsm.android.v3.ui.mypage

interface MyPageContract{

    fun showDialogInstitutionReport()
    fun showDialogBugReport()
    fun showDialogLogout()

    fun intentQuestionResearch()
    fun intentPasswordChange()
    fun intentMeriteHistory()
    fun intentintroDevelopers()

    interface LogoutContract{
        fun exitLogut()
    }

    interface InstitutionReportContract{
        fun exitInstitutionReport()
    }

    interface BugReportContract{
        fun exitBugReport()
    }
}