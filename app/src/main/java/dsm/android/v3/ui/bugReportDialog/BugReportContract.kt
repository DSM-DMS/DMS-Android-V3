package dsm.android.v3.ui.bugReportDialog

interface BugReportContract{
    fun exitBugReport()
    fun flagBugTitleBlankError()
    fun flagBugContentBlankError()
    fun createShortToast(text: String)
}