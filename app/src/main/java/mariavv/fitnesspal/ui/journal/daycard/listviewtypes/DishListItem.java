package mariavv.fitnesspal.ui.journal.daycard.listviewtypes;

import android.support.v7.widget.RecyclerView;

import mariavv.fitnesspal.model.model.Dish;
import mariavv.fitnesspal.model.model.Energy;
import mariavv.fitnesspal.model.model.MacroNutrients;
import mariavv.fitnesspal.model.model.Weight;
import mariavv.fitnesspal.ui.journal.daycard.ItemType;

public class DishListItem implements ItemType {

    public Dish dish;
    public MacroNutrients macroNutrients;
    public Weight weight;
    public Energy energy;

    public DishListItem(Dish dish, MacroNutrients macroNutrients, Weight weight, Energy energy) {
        this.dish = dish;
        this.macroNutrients = macroNutrients;
        this.weight = weight;
        this.energy = energy;
    }

    @Override
    public int getItemViewType() {
        return ItemType.DISH;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder) {

    }
}
