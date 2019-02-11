package mariavv.fitnesspal.ui.journal.daycard.listviewtypes;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

import mariavv.fitnesspal.model.model.Energy;
import mariavv.fitnesspal.model.model.MacroNutrients;
import mariavv.fitnesspal.model.model.Weight;
import mariavv.fitnesspal.ui.journal.daycard.ItemType;
import mariavv.fitnesspal.ui.journal.daycard.ViewHolderFactory;

public class HeaderListItem implements ItemType {

    @NonNull
    public MacroNutrients macroNutrients;

    public HeaderListItem(@NonNull MacroNutrients macroNutrients) {
        this.macroNutrients = macroNutrients;
    }

    @Override
    public int getItemViewType() {
        return ItemType.HEADER;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder) {
        ViewHolderFactory.HeaderViewHolder holder = (ViewHolderFactory.HeaderViewHolder) viewHolder;
        holder.energyTv.setText(String.valueOf(new Energy(macroNutrients, new Weight(100)).getEnergy()));
    }
}
