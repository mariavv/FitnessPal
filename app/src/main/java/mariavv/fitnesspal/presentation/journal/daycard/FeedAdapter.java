package mariavv.fitnesspal.presentation.journal.daycard;

import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import mariavv.fitnesspal.data.db.CName;
import mariavv.fitnesspal.data.db.Meal;
import mariavv.fitnesspal.domain.Food;
import mariavv.fitnesspal.presentation.journal.daycard.listviewtypes.DishListItem;
import mariavv.fitnesspal.presentation.journal.daycard.listviewtypes.MealListItem;

class FeedAdapter extends RecyclerView.Adapter {
    private List<ItemType> dataSet = new ArrayList<>();

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        return ViewHolderFactory.create(viewGroup, viewType);
    }

    @Override
    public int getItemViewType(int position) {
        return dataSet.get(position).getItemViewType();
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        //todo??
        dataSet.get(position).onBindViewHolder(viewHolder);
    }

    @Override
    public int getItemCount() {
        if (dataSet != null) {
            return dataSet.size();
        }
        return 0;
    }

    void updateItems(@NonNull Cursor c) {
        c.moveToFirst();

        final List<ItemType> dataSet = new ArrayList<>();

        //каша

        //final HeaderListItem headerListItem = new HeaderListItem(0, 0, 0);
        //dataSet.add(headerListItem);

        final MealListItem mealBreakfast = new MealListItem(Meal.BREAKFAST, 0);
        final MealListItem mealLanch = new MealListItem(Meal.LANCH, 0);
        final MealListItem mealDinner = new MealListItem(Meal.DINNER, 0);

        final List<DishListItem> dishesBreakfast = new ArrayList<>();
        final List<DishListItem> dishesLanch = new ArrayList<>();
        final List<DishListItem> dishesDinner = new ArrayList<>();

        do {
            final int protein = c.getInt(c.getColumnIndex(CName.PROTEIN));
            final int fat = c.getInt(c.getColumnIndex(CName.FAT));
            final int carb = c.getInt(c.getColumnIndex(CName.CARB));
            final Food food = new Food(c.getString(c.getColumnIndex(CName.NAME)), protein, fat, carb);
            final DishListItem dish = new DishListItem(food, c.getInt(c.getColumnIndex(CName.WEIGHT)));

            //headerListItem.protein += protein;
            //headerListItem.fat += fat;
            //headerListItem.carb += carb;

            switch (c.getString(c.getColumnIndex(CName.MEAL))) {
                case Meal.BREAKFAST:
                    dishesBreakfast.add(dish);
                    mealBreakfast.energy += food.getEnergy(dish.weight);
                    break;
                case Meal.LANCH:
                    dishesLanch.add(dish);
                    mealLanch.energy += food.getEnergy(dish.weight);
                    break;
                case Meal.DINNER:
                    dishesDinner.add(dish);
                    mealDinner.energy += food.getEnergy(dish.weight);
                    break;
            }

        } while (c.moveToNext());

        //падает
        //c.close();

        if (!dishesBreakfast.isEmpty()) {
            dataSet.add(mealBreakfast);
            dataSet.addAll(dishesBreakfast);
        }
        if (!dishesLanch.isEmpty()) {
            dataSet.add(mealLanch);
            dataSet.addAll(dishesLanch);
        }
        if (!dishesDinner.isEmpty()) {
            dataSet.add(mealDinner);
            dataSet.addAll(dishesDinner);
        }

        this.dataSet = dataSet;

        notifyDataSetChanged();
    }
}
