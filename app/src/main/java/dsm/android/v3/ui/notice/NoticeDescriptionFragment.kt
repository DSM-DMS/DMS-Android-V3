package dsm.android.v3.ui.notice

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.design.widget.BottomSheetDialogFragment
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.navigation.fragment.findNavController
import dsm.android.v3.R

class NoticeDescriptionFragment : BottomSheetDialogFragment() {

    val viewModel by lazy { ViewModelProviders.of(activity!!)[NoticeViewModel::class.java] }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_notice_description, container, false) as View

        view.findViewById<ImageView>(R.id.notice_description_cancle_iv).setOnClickListener {
            findNavController().popBackStack()
        }

        return view
    }


    fun frameDate(date: String): String {
        val day = date.substring(9, 10)
        val month = date.substring(6, 7)
        val year = date.substring(0, 4)
        return "사감부에서 ${year}년 ${month}월 ${day}일에 게시한 글입니다."
    }
}