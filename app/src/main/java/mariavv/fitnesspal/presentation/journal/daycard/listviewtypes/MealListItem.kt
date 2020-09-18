package mariavv.fitnesspal.presentation.journal.daycard.listviewtypes

import android.support.v7.widget.RecyclerView

import mariavv.fitnesspal.App
import mariavv.fitnesspal.R
import mariavv.fitnesspal.data.db.Meal
import mariavv.fitnesspal.presentation.journal.daycard.ItemType
import mariavv.fitnesspal.presentation.journal.daycard.ViewHolderFactory

class MealListItem(var meal: Meal, var energy: Int) : ItemType {

    override val itemViewType: Int
        get() = ItemType.MEAL

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder) {
        val holder = viewHolder as ViewHolderFactory.ListItemViewHolder
        holder.mealTitleTv?.text = meal.value
        holder.energyTv?.text = String.format("%s %s", energy.toString(), App.context.getString(R.string.energy_postfix))
    }
}
