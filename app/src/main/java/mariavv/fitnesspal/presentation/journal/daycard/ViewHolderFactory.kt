package mariavv.fitnesspal.presentation.journal.daycard

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import mariavv.fitnesspal.R

object ViewHolderFactory {

    internal fun create(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view: View
        when (viewType) {
            ItemType.HEADER -> {
                view = LayoutInflater.from(parent.context).inflate(R.layout.day_header_item, parent, false)
                return ViewHolderFactory.HeaderViewHolder(view)
            }

            ItemType.MEAL -> {
                view = LayoutInflater.from(parent.context).inflate(R.layout.day_meal_list_item, parent, false)
                return ViewHolderFactory.ListItemViewHolder(view)
            }

            ItemType.DISH -> {
                view = LayoutInflater.from(parent.context).inflate(R.layout.day_dish_list_item, parent, false)
                return ViewHolderFactory.ListItemViewHolder(view)
            }

            else -> return ViewHolderFactory.ListItemViewHolder(View(parent.context))
        }
    }

    class HeaderViewHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var proteinTv: TextView = itemView.findViewById(R.id.protein_tv)
        var fatTv: TextView = itemView.findViewById(R.id.fat_tv)
        var carbTv: TextView = itemView.findViewById(R.id.carb_tv)
        var energyTv: TextView = itemView.findViewById(R.id.energy_tv)
    }

    class ListItemViewHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var mealTitleTv: TextView? = itemView.findViewById(R.id.meal_title_tv)
        var weightTv: TextView? = itemView.findViewById(R.id.weight_tv)
        var energyTv: TextView? = itemView.findViewById(R.id.energy_tv)
    }
}
