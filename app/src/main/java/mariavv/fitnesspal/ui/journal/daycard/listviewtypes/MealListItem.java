package mariavv.fitnesspal.ui.journal.daycard.listviewtypes;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

import mariavv.fitnesspal.FitnessPal;
import mariavv.fitnesspal.R;
import mariavv.fitnesspal.model.model.Energy;
import mariavv.fitnesspal.model.model.MacroNutrients;
import mariavv.fitnesspal.model.model.MealNum;
import mariavv.fitnesspal.ui.journal.daycard.ItemType;
import mariavv.fitnesspal.ui.journal.daycard.ViewHolderFactory;

public class MealListItem implements ItemType {

    @NonNull
    public MealNum mealNum;
    @NonNull
    public MacroNutrients macroNutrients;

    public MealListItem(@NonNull MealNum mealNum, @NonNull MacroNutrients macroNutrients) {
        this.mealNum = mealNum;
        this.macroNutrients = macroNutrients;
    }

    @Override
    public int getItemViewType() {
        return ItemType.MEAL;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder) {
        ViewHolderFactory.ListItemViewHolder holder = (ViewHolderFactory.ListItemViewHolder) viewHolder;
        holder.mealTitleTv.setText(String.format("%s%s", String.valueOf(mealNum.value), FitnessPal.appContext.getString(R.string.meal_num_postfix)));
        holder.proteinTv.setText(String.format("%s%s", FitnessPal.appContext.getString(R.string.protein_prefix), String.valueOf(macroNutrients.protein)));
        holder.fatTv.setText(String.format("%s%s", FitnessPal.appContext.getString(R.string.fat_prefix), String.valueOf(macroNutrients.fat)));
        holder.carbTv.setText(String.format("%s%s", FitnessPal.appContext.getString(R.string.carb_prefix), String.valueOf(macroNutrients.carb)));
        holder.energyTv.setText(String.format("%s%s", String.valueOf(new Energy(macroNutrients).getEnergy()), FitnessPal.appContext.getString(R.string.energy_postfix)));
    }
}
