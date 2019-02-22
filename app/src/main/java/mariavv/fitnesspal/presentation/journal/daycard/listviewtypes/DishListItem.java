package mariavv.fitnesspal.presentation.journal.daycard.listviewtypes;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

import mariavv.fitnesspal.FitnessPal;
import mariavv.fitnesspal.R;
import mariavv.fitnesspal.domain.Food;
import mariavv.fitnesspal.presentation.journal.daycard.ItemType;
import mariavv.fitnesspal.presentation.journal.daycard.ViewHolderFactory;

public class DishListItem implements ItemType {

    @NonNull
    public Food food;
    public int weight;

    public DishListItem(@NonNull Food food, int weight) {
        this.food = food;
        this.weight = weight;
    }

    @Override
    public int getItemViewType() {
        return ItemType.DISH;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder) {
        ViewHolderFactory.ListItemViewHolder holder = (ViewHolderFactory.ListItemViewHolder) viewHolder;
        holder.mealTitleTv.setText(food.getName());
        holder.weightTv.setText(String.format("%s %s",
                String.valueOf(weight),
                FitnessPal.appContext.getString(R.string.weight_postfix)));
        holder.energyTv.setText(String.format("%s %s", String.valueOf(food.getEnergy(weight)), FitnessPal.appContext.getString(R.string.energy_postfix)));
    }
}
