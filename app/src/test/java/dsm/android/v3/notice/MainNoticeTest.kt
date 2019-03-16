package dsm.android.v3.notice

import org.junit.Test

import junit.framework.Assert

class MainNoticeTest {

    @Test
    fun 날짜문자열조작테스트 () {
        val date = "2010301030312903120"
        Assert.assertEquals(date.substring(0, 4), 20103)
    }
}