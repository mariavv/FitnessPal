package mariavv.fitnesspal.ui.journal.daycard.listviewtypes;

import android.support.v7.widget.RecyclerView;

import mariavv.fitnesspal.model.model.Energy;
import mariavv.fitnesspal.model.model.MacroNutrients;
import mariavv.fitnesspal.model.model.MealNum;
import mariavv.fitnesspal.ui.journal.daycard.ItemType;

public class MealListItem implements ItemType {

    public MealNum mealNum;
    public MacroNutrients macroNutrients;
    public Energy energy;

    public MealListItem(MealNum mealNum, MacroNutrients macroNutrients, Energy energy) {
        this.mealNum = mealNum;
        this.macroNutrients = macroNutrients;
        this.energy = energy;
    }

    @Override
    public int getItemViewType() {
        return ItemType.MEAL;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder) {

    }
}
