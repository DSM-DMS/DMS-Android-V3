package dsm.android.v3.ui.applyGoingOut

import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.support.design.widget.AppBarLayout
import android.support.v7.app.ActionBar
import android.support.v7.app.AppCompatActivity
import androidx.navigation.findNavController
import dsm.android.v3.R
import kotlinx.android.synthetic.main.activity_apply_going_out.*

class ApplyGoingOutActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_apply_going_out)

        setSupportActionBar(going_out_toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.title = "외출 신청"
        going_out_toolbar.setNavigationOnClickListener { onBackPressed() }

        val bundle = Bundle().apply { putParcelable("actionBar", ActionBarParcel(supportActionBar)) }
        findNavController(R.id.going_out_fragment).navigate(R.id.applyGoingFragment, bundle)
    }

    override fun onBackPressed() {
        if (findNavController(R.id.going_out_fragment).currentDestination!!.label.toString() == "applyGoingFragment")
            finish()
        else
            super.onBackPressed()
    }
}

data class ActionBarParcel(val actionBar: ActionBar?) : Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readValue(AppBarLayout::class.java.classLoader) as ActionBar
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeValue(actionBar)
    }

    companion object CREATOR : Parcelable.Creator<ActionBarParcel> {
        override fun createFromParcel(parcel: Parcel): ActionBarParcel {
            return ActionBarParcel(parcel)
        }

        override fun newArray(size: Int): Array<ActionBarParcel?> {
            return arrayOfNulls(size)
        }
    }
}