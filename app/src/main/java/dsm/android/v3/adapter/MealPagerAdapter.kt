package dsm.android.v3.adapter

import android.support.v4.view.PagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import dsm.android.v3.R
import dsm.android.v3.connecter.Connecter
import dsm.android.v3.model.MealModel
import org.jetbrains.anko.find
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*


class MealPagerAdapter(val models: ArrayList<String>) : PagerAdapter() {
    override fun getCount() = models.size

    override fun destroyItem(container: ViewGroup, position: Int, any: Any) = container.removeView(any as View)

    override fun isViewFromObject(p0: View, p1: Any) = p0 == p1

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val inflater = LayoutInflater.from(container.context)
        val view = inflater.inflate(R.layout.item_meal, container, false)

        val model = models[position]

        val breakfast = view.find<TextView>(R.id.mealItem_breakfast_content_tv)
        val lunch = view.find<TextView>(R.id.mealItem_lunch_content_tv)
        val dinner = view.find<TextView>(R.id.mealItem_dinner_content_tv)

//        Connecter.api.getMeal(dataFormat.format(calender.time)).enqueue(object : Callback<MealModel> {
        Connecter.api.getMeal(model).enqueue(object : Callback<MealModel> {
            override fun onResponse(call: Call<MealModel>, response: Response<MealModel>) {
                when (response.code()) {
                    200 -> {
                        val body = response.body()
                        breakfast.text = body?.breakfast?.flatten() ?: "급식이 없습니다 $model"
                        lunch.text = body?.lunch?.flatten() ?: "급식이 없습니다."
                        dinner.text = body?.dinner?.flatten() ?: "급식이 없습니다"
                        notifyDataSetChanged()
                    }
                }
            }

            override fun onFailure(call: Call<MealModel>, t: Throwable) {
                breakfast.text = "급식이 없습니다"
                lunch.text = "급식이 없습니다"
                dinner.text = "급식이 없습니다"
            }

        })

        container.addView(view)
        return view
    }

    fun ArrayList<String>.flatten(): String {
        val builder = StringBuilder()
        forEach {
            builder.append(it)
        }
        return builder.toString()
    }
}