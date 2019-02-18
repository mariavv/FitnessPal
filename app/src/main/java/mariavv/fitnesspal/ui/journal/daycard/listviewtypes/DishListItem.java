package mariavv.fitnesspal.ui.journal.daycard.listviewtypes;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

import mariavv.fitnesspal.FitnessPal;
import mariavv.fitnesspal.R;
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
        holder.weightTv.setText(String.format("%s%s", String.valueOf(weight.value), FitnessPal.appContext.getString(R.string.weight_postfix)));
        holder.energyTv.setText(String.format("%s%s", String.valueOf(new Energy(getMacroNutrients()).getEnergy()), FitnessPal.appContext.getString(R.string.energy_postfix)));
    }
}
