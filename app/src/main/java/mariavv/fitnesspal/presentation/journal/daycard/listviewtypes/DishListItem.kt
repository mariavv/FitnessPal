package mariavv.fitnesspal.presentation.journal.daycard.listviewtypes

import android.support.v7.widget.RecyclerView

import mariavv.fitnesspal.App
import mariavv.fitnesspal.R
import mariavv.fitnesspal.domain.Food
import mariavv.fitnesspal.presentation.journal.daycard.ItemType
import mariavv.fitnesspal.presentation.journal.daycard.ViewHolderFactory

class DishListItem(var food: Food, var weight: Int) : ItemType {

    override val itemViewType: Int
        get() = ItemType.DISH

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder) {
        val holder = viewHolder as ViewHolderFactory.ListItemViewHolder
        holder.mealTitleTv?.text = food.name
        holder.weightTv?.text = String.format("%s %s", weight.toString(), App.context.getString(R.string.weight_postfix))
        holder.energyTv?.text = String.format("%s %s", food.getEnergy(weight).toString(), App.context.getString(R.string.energy_postfix))
    }
}
