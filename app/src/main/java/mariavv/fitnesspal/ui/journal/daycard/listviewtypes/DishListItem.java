package mariavv.fitnesspal.ui.journal.daycard.listviewtypes;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

import mariavv.fitnesspal.model.model.Energy;
import mariavv.fitnesspal.model.model.FoodName;
import mariavv.fitnesspal.model.model.MacroNutrients;
import mariavv.fitnesspal.model.model.Weight;
import mariavv.fitnesspal.ui.journal.daycard.ItemType;
import mariavv.fitnesspal.ui.journal.daycard.ViewHolderFactory;

import static mariavv.fitnesspal.model.model.Energy.PER_WEIGHT;

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
        return new MacroNutrients(macroNutrients.protein * weight.value / PER_WEIGHT,
                macroNutrients.fat * weight.value / PER_WEIGHT,
                macroNutrients.carb * weight.value / PER_WEIGHT);
    }

    @Override
    public int getItemViewType() {
        return ItemType.DISH;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder) {
        ViewHolderFactory.ListItemViewHolder holder = (ViewHolderFactory.ListItemViewHolder) viewHolder;
        holder.mealTitleTv.setText(name.value);
        holder.weightTv.setText(String.valueOf(weight.value));
        holder.proteinTv.setText(String.valueOf(getMacroNutrients().protein));
        holder.fatTv.setText(String.valueOf(getMacroNutrients().fat));
        holder.carbTv.setText(String.valueOf(getMacroNutrients().carb));
        holder.energyTv.setText(String.valueOf(new Energy(macroNutrients, weight).getEnergy()));
    }
}
