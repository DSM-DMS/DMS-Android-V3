package dsm.android.v3.ui.introduceTeam

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import dsm.android.v3.R
import dsm.android.v3.adapter.IntroDeveloperAdapter
import dsm.android.v3.model.IntroDeveloperModel
import kotlinx.android.synthetic.main.activity_intro_developer.*

class IntroDeveloperActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro_developer)
        introDeveloper_recyclerView.layoutManager = GridLayoutManager(this,2,GridLayoutManager.VERTICAL,false)
        val idm = IntroDeveloperModel("이종현", "골드 5", "sibal")
        introDeveloper_recyclerView.adapter = IntroDeveloperAdapter(arrayListOf(idm, idm, idm, idm))
    }
}
