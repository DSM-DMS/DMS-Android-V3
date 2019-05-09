package dsm.android.v3.ui.adapter

import android.support.v4.view.PagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import dsm.android.v3.R
import org.jetbrains.anko.find
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class MealPagerAdapter(private val dates: ArrayList<String>) : PagerAdapter() {
    override fun getCount() = dates.size

    override fun destroyItem(container: ViewGroup, position: Int, any: Any) = container.removeView(any as View)

    override fun isViewFromObject(p0: View, p1: Any) = p0 == p1

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val inflater = LayoutInflater.from(container.context)
        val view = inflater.inflate(R.layout.item_meal, container, false)

        val date = dates[position]

        val breakfast = view.find<TextView>(R.id.mealItem_breakfast_content_tv)
        val lunch = view.find<TextView>(R.id.mealItem_lunch_content_tv)
        val dinner = view.find<TextView>(R.id.mealItem_dinner_content_tv)

        Connecter.api.getMeal(date).enqueue(object : Callback<JsonObject> {
            override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                when (response.code()) {
                    200 -> {
                        val body = response.body()!![date].asJsonObject
                        breakfast.text =
                            if (body.has("breakfast")) body.getAsJsonArray("breakfast").flatten()
                            else "급식이 없습니다."

                        lunch.text =
                            if (body.has("lunch")) body.getAsJsonArray("lunch").flatten()
                            else "급식이 없습니다."

                        dinner.text =
                            if (body.has("dinner")) body.getAsJsonArray("dinner").flatten()
                            else "급식이 없습니다."

                        notifyDataSetChanged()
                    }
                    else -> {
                        breakfast.text = "네트워크"
                        lunch.text = "상태를"
                        dinner.text = "확인해주세요"
                        notifyDataSetChanged()
                    }
                }
            }

            override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                breakfast.text = "네트워크"
                lunch.text = "상태를"
                dinner.text = "확인해주세요"
                notifyDataSetChanged()
            }

        })

        container.addView(view)
        return view
    }

    fun JsonArray.flatten(): String {
        val builder = StringBuilder()
        return if (size() > 1) {
            forEach {
                builder.append("${it.asString}, ")
            }
            builder.delete(builder.lastIndex - 1, builder.lastIndex).toString()
        } else
            "급식이 없습니다."
    }
}