package mariavv.fitnesspal.presentation.journal.daycard.listviewtypes

import android.support.v7.widget.RecyclerView

import mariavv.fitnesspal.App
import mariavv.fitnesspal.R
import mariavv.fitnesspal.presentation.journal.daycard.ItemType
import mariavv.fitnesspal.presentation.journal.daycard.ViewHolderFactory

class HeaderListItem(var protein: Int, var fat: Int, var carb: Int) : ItemType {

    override val itemViewType: Int
        get() = ItemType.HEADER

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder) {
        val holder = viewHolder as ViewHolderFactory.HeaderViewHolder

        val weightPostfx = App.getAppString(R.string.weight_postfix)

        holder.proteinTv.setText(String.format("%s %s %s",
                App.getAppString(R.string.protein_full_prefix),
                protein.toString(),
                weightPostfx))

        holder.fatTv.setText(String.format("%s %s %s",
                App.getAppString(R.string.fat_full_prefix),
                fat.toString(),
                weightPostfx))

        holder.carbTv.setText(String.format("%s %s %s",
                App.getAppString(R.string.carb_full_prefix),
                carb.toString(),
                weightPostfx))

        holder.energyTv.setText(String.format("%s%s%s",
                App.getAppString(R.string.energy_prefix),
                0.toString(),
                App.getAppString(R.string.energy_postfix)))
    }
}
