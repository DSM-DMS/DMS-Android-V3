package dsm.android.v3.ui.institutionReportDialog

interface InstitutionReportContract{
    fun exitInstitutionReport()
    fun flagInstitutionTitleBlankError()
    fun flagInstitutionRoomNumberBlankError()
    fun flagInstitutionContentBlankError()
    fun createShortToast(text: String)
}