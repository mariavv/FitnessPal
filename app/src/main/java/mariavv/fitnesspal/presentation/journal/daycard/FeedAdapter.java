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

        //каша

        final MealListItem mealBreakfast = new MealListItem(Meal.BREAKFAST, 0);
        final MealListItem mealLanch = new MealListItem(Meal.LANCH, 0);
        final MealListItem mealDinner = new MealListItem(Meal.DINNER, 0);

        final List<DishListItem> dishesBreakfast = new ArrayList<>();
        final List<DishListItem> dishesLanch = new ArrayList<>();
        final List<DishListItem> dishesDinner = new ArrayList<>();

        do {
            switch (c.getString(c.getColumnIndex(CName.MEAL))) {
                case Meal.BREAKFAST:
                    addtoMealList(c, mealBreakfast, dishesBreakfast);
                    break;
                case Meal.LANCH:
                    addtoMealList(c, mealLanch, dishesLanch);
                    break;
                case Meal.DINNER:
                    addtoMealList(c, mealDinner, dishesDinner);
                    break;
            }

        } while (c.moveToNext());

        //падает
        //c.close();

        addToDataSet(mealBreakfast, dishesBreakfast);
        addToDataSet(mealLanch, dishesLanch);
        addToDataSet(mealDinner, dishesDinner);

        notifyDataSetChanged();
    }

    private void addtoMealList(Cursor c, MealListItem meal, List<DishListItem> dishes) {
        final int protein = c.getInt(c.getColumnIndex(CName.PROTEIN));
        final int fat = c.getInt(c.getColumnIndex(CName.FAT));
        final int carb = c.getInt(c.getColumnIndex(CName.CARB));
        final Food food = new Food(c.getString(c.getColumnIndex(CName.NAME)), protein, fat, carb);
        final DishListItem dish = new DishListItem(food, c.getInt(c.getColumnIndex(CName.WEIGHT)));

        dishes.add(dish);
        meal.energy += food.getEnergy(dish.weight);
    }

    private void addToDataSet(MealListItem meal, List<DishListItem> dishes) {
        if (!dishes.isEmpty()) {
            this.dataSet.add(meal);
            this.dataSet.addAll(dishes);
        }
    }
}
