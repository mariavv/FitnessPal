package mariavv.fitnesspal.presentation.journal.daycard.listviewtypes;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

import mariavv.fitnesspal.FitnessPal;
import mariavv.fitnesspal.R;
import mariavv.fitnesspal.data.db.Meal;
import mariavv.fitnesspal.presentation.journal.daycard.ItemType;
import mariavv.fitnesspal.presentation.journal.daycard.ViewHolderFactory;

public class MealListItem implements ItemType {

    @NonNull
    public Meal meal;
    public int energy;

    public MealListItem(@NonNull Meal meal, int energy) {
        this.meal = meal;
        this.energy = energy;
    }

    @Override
    public int getItemViewType() {
        return ItemType.MEAL;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder) {
        ViewHolderFactory.ListItemViewHolder holder = (ViewHolderFactory.ListItemViewHolder) viewHolder;
        holder.mealTitleTv.setText(meal.getValue());
        holder.energyTv.setText(String.format("%s %s",
                String.valueOf(energy),
                FitnessPal.appContext.getString(R.string.energy_postfix)));
    }
}
