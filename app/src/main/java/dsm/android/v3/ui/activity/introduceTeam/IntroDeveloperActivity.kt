package dsm.android.v3.ui.activity.introduceTeam

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import dsm.android.v3.R
import dsm.android.v3.ui.adapter.IntroDeveloperAdapter
import dsm.android.v3.presentation.model.IntroDeveloperModel
import kotlinx.android.synthetic.main.activity_intro_developer.*

class IntroDeveloperActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro_developer)
        val developers = arrayListOf(
            IntroDeveloperModel("최이삭", "프론트엔드"),
            IntroDeveloperModel("이수하", "프론트엔드"),
            IntroDeveloperModel("김형규", "프론트엔드"),
            IntroDeveloperModel("오인서", "프론트엔드"),
            IntroDeveloperModel("이주용", "안드로이드"),
            IntroDeveloperModel("송진우", "안드로이드"),
            IntroDeveloperModel("이성현", "안드로이드"),
            IntroDeveloperModel("이종현", "안드로이드/CS"),
            IntroDeveloperModel("유동근", "Windows&CS"),
            IntroDeveloperModel("김영찬", "디자인"),
            IntroDeveloperModel("윤석민", "디자인"),
            IntroDeveloperModel("김동규", "디자인"),
            IntroDeveloperModel("이동기", "iOS"),
            IntroDeveloperModel("인상민", "백엔드"),
            IntroDeveloperModel("김윤재", "백엔드")
        )
        introDeveloper_recyclerView.adapter = IntroDeveloperAdapter(developers)
        introDeveloper_recyclerView.adapter!!.notifyDataSetChanged()
    }
}
