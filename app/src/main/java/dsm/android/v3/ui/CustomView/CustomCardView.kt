package dsm.android.v3.ui.CustomView

import android.content.Context
import android.content.res.TypedArray
import android.support.v4.content.ContextCompat
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import dsm.android.v3.R
import kotlinx.android.synthetic.main.customview_cardview.view.*

class CustomCardView : FrameLayout {

    constructor(context: Context) : super(context) {
        initView()
    }

    constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet) {
        initView()
        getAttrs(attributeSet)
    }

    constructor(context: Context, attributeSet: AttributeSet, defStyle: Int) : super(context, attributeSet, defStyle) {
        initView()
        getAttrs(attributeSet, defStyle)
    }

    private fun initView() {
        val infService = Context.LAYOUT_INFLATER_SERVICE
        val layoutInflater = context.getSystemService(infService) as LayoutInflater
        val v = layoutInflater.inflate(R.layout.customview_cardview, this, false)
        addView(v)
    }

    private fun getAttrs(attrs: AttributeSet) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomView)
        setTypedArray(typedArray)
    }

    private fun getAttrs(attrs: AttributeSet, defStyle: Int) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomView, defStyle, 0)
        setTypedArray(typedArray)
    }

    private fun setTypedArray(typedArray: TypedArray) {
        val button = typedArray.getResourceId(R.styleable.CustomView_button, R.drawable.ic_arrow_right)
        customview_button_iv.setBackgroundResource(button)

        val title: String = typedArray.getString(R.styleable.CustomView_title)!!
        customview_title_tv.text = title

        val description = typedArray.getString(R.styleable.CustomView_description)
        customview_description_tv.text = description

        val count = typedArray.getInteger(R.styleable.CustomView_count, 0)
        setCustomCount(count)

        val titleColor = typedArray.getColor(R.styleable.CustomView_titleColor, ContextCompat.getColor(context, R.color.colorPrimary))
        customview_title_tv.setTextColor(titleColor)

        val descriptionColor = typedArray.getColor(R.styleable.CustomView_descriptionColor, ContextCompat.getColor(context, R.color.colorGray800))
        customview_description_tv.setTextColor(descriptionColor)

        val bgColor = typedArray.getColor(R.styleable.CustomView_backgroundColor, ContextCompat.getColor(context, R.color.colorWhite))
        customview_top_cv.setCardBackgroundColor(bgColor)

        val imageVisible = typedArray.getBoolean(R.styleable.CustomView_imageVisible, true)
        if(imageVisible) customview_button_iv.visibility = View.VISIBLE else customview_button_iv.visibility = View.GONE

        typedArray.recycle()
    }

    fun setCustomCount(count: Int) {
        when (count) {
            1 -> {
                customview_bottom_cv.visibility = View.GONE
                customview_middle_cv.visibility = View.GONE
            }

            2 -> {
                customview_middle_cv.visibility = View.VISIBLE
                customview_bottom_cv.visibility = View.GONE
            }

            3 -> {
                customview_middle_cv.visibility = View.VISIBLE
                customview_bottom_cv.visibility = View.VISIBLE
            }

            else -> {
                customview_middle_cv.visibility = View.GONE
                customview_bottom_cv.visibility = View.VISIBLE
            }
        }
    }

    fun setCustomText(title: String, description : String) {
        customview_title_tv.text = title
        customview_description_tv.text = description
    }

    fun setCustomImageView(resource : Int) {
        customview_button_iv.setBackgroundResource(resource)
    }
}