package dsm.android.v3.ui.applyMusicDom

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import dsm.android.v3.R
import dsm.android.v3.util.saveToken
import kotlinx.android.synthetic.main.activity_apply_music_dom.*

class ApplyMusicDomActivity : AppCompatActivity() {
//    val navController by lazy {
//        findNavController(R.id.applyMusic_fragmentContainer)
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_apply_music_dom)
        setSupportActionBar(apply_musicDom_toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true);
        supportActionBar?.setDisplayShowHomeEnabled(true);
        title = "기상음악 신청"
        apply_musicDom_toolbar.setNavigationOnClickListener {
            onBackPressed()
        }
        findNavController(R.id.applyMusic_fragmentContainer)
        saveToken(
            baseContext,
            "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE1NTI3NTE2NzgsIm5iZiI6MTU1Mjc1MTY3OCwianRpIjoiODI4YzA1OGUtNjNhNi00Mzg0LTkxOWQtZmY2YmNiZmJhODM3IiwiZXhwIjoxNTUyNzU1Mjc4LCJpZGVudGl0eSI6InRlc3QiLCJmcmVzaCI6ZmFsc2UsInR5cGUiOiJhY2Nlc3MifQ.9_OcNb8p-5FrldTj0OvDvy3I-rKteldX4a8a4z7Dtlw"
        )
    }
}
