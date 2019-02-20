package mariavv.fitnesspal.presentation.journal.daycard.listviewtypes;

import android.support.v7.widget.RecyclerView;

import mariavv.fitnesspal.FitnessPal;
import mariavv.fitnesspal.R;
import mariavv.fitnesspal.presentation.journal.daycard.ItemType;
import mariavv.fitnesspal.presentation.journal.daycard.ViewHolderFactory;

public class HeaderListItem implements ItemType {

    public int protein;
    public int fat;
    public int carb;

    public HeaderListItem(int protein, int fat, int carb) {
        this.protein = protein;
        this.fat = fat;
        this.carb = carb;
    }

    @Override
    public int getItemViewType() {
        return ItemType.HEADER;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder) {
        ViewHolderFactory.HeaderViewHolder holder = (ViewHolderFactory.HeaderViewHolder) viewHolder;

        String weightPostfx = FitnessPal.getAppString(R.string.weight_postfix);

        holder.proteinTv.setText(String.format("%s%s%s",
                FitnessPal.getAppString(R.string.protein_full_prefix),
                String.valueOf(protein),
                weightPostfx));

        holder.fatTv.setText(String.format("%s%s%s",
                FitnessPal.getAppString(R.string.fat_full_prefix),
                String.valueOf(fat),
                weightPostfx));

        holder.carbTv.setText(String.format("%s%s%s",
                FitnessPal.getAppString(R.string.carb_full_prefix),
                String.valueOf(carb),
                weightPostfx));

        holder.energyTv.setText(String.format("%s%s%s",
                FitnessPal.getAppString(R.string.energy_prefix),
                String.valueOf(0),
                FitnessPal.getAppString(R.string.energy_postfix)));
    }
}
