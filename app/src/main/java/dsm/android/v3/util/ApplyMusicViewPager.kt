package dsm.android.v3.util

import android.content.Context
import android.support.v4.view.ViewPager
import android.util.AttributeSet
import android.view.MotionEvent


class ApplyMusicViewPager : ViewPager {
    constructor(context: Context) : super(context)

    constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet)

    override fun onTouchEvent(ev: MotionEvent): Boolean {
        return if (currentItem == 0 && childCount == 0) {
            false
        } else super.onTouchEvent(ev)

    }

    override fun onInterceptTouchEvent(ev: MotionEvent): Boolean {
        return if (currentItem == 0 && childCount == 0) {
            false
        } else super.onInterceptTouchEvent(ev)

    }
}