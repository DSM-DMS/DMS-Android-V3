package dsm.android.v3.adapter

import android.support.v4.view.PagerAdapter
import android.view.View
import android.view.ViewGroup
import dsm.android.v3.model.MealModel
import android.view.LayoutInflater
import android.widget.TextView
import dsm.android.v3.R
import org.jetbrains.anko.find


class MealPagerAdapter(val models: ArrayList<MealModel>) : PagerAdapter() {
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

        model.breakfast.forEach { breakfast.append("$it, ") }
        breakfast.text.run { removeRange(lastIndex -1, lastIndex) }
        model.lunch.forEach { lunch.append("$it, ") }
        lunch.text.run { removeRange(lastIndex -1, lastIndex) }
        model.dinner.forEach { dinner.append("$it, ") }
        dinner.text.run { removeRange(lastIndex -1, lastIndex) }

        container.addView(view)
        return view
    }

}