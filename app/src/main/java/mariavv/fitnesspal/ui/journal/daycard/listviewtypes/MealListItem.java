package mariavv.fitnesspal.ui.journal.daycard.listviewtypes;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

import mariavv.fitnesspal.model.model.MacroNutrients;
import mariavv.fitnesspal.model.model.MealNum;
import mariavv.fitnesspal.ui.journal.daycard.ItemType;

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

    }
}
