package mariavv.fitnesspal.ui.journal.daycard.listviewtypes;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

import mariavv.fitnesspal.model.model.Energy;
import mariavv.fitnesspal.model.model.FoodName;
import mariavv.fitnesspal.model.model.MacroNutrients;
import mariavv.fitnesspal.model.model.Weight;
import mariavv.fitnesspal.ui.journal.daycard.ItemType;

public class DishListItem implements ItemType {

    @NonNull
    public FoodName name;
    @NonNull
    public Weight weight;
    @NonNull
    private MacroNutrients macroNutrients;

    public DishListItem(@NonNull FoodName name, @NonNull MacroNutrients macroNutrients, @NonNull Weight weight) {
        this.name = name;
        this.macroNutrients = macroNutrients;
        this.weight = weight;
    }

    public Energy getEnergy() {
        return new Energy(macroNutrients, weight);
    }

    @NonNull
    public MacroNutrients getMacroNutrients() {
        return new MacroNutrients(macroNutrients.protein * weight.value / 100,
                macroNutrients.fat * weight.value / 100,
                macroNutrients.carb * weight.value / 100);
    }

    @Override
    public int getItemViewType() {
        return ItemType.DISH;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder) {

    }
}
