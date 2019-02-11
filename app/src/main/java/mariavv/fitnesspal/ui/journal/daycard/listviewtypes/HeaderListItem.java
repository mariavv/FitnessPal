package mariavv.fitnesspal.ui.journal.daycard.listviewtypes;

import android.support.v7.widget.RecyclerView;

import mariavv.fitnesspal.model.model.Energy;
import mariavv.fitnesspal.model.model.MacroNutrients;
import mariavv.fitnesspal.ui.journal.daycard.ItemType;
import mariavv.fitnesspal.ui.journal.daycard.ViewHolderFactory;

public class HeaderListItem implements ItemType {

    public MacroNutrients macroNutrients;
    public Energy energy;

    public HeaderListItem(MacroNutrients macroNutrients, Energy energy) {
        this.macroNutrients = macroNutrients;
        this.energy = energy;
    }

    @Override
    public int getItemViewType() {
        return ItemType.HEADER;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder) {
        ViewHolderFactory.HeaderViewHolder holder = (ViewHolderFactory.HeaderViewHolder) viewHolder;
        holder.energyTv.setText(String.valueOf(energy.energy));
    }
}
