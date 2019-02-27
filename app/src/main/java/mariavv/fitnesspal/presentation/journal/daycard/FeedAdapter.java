package mariavv.fitnesspal.presentation.journal.daycard;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

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

    void updateItems(@NonNull List<ItemType> dataSet) {
        /*c.moveToFirst();

        //каша

        final MealListItem mealBreakfast = new MealListItem(Meal.BREAKFAST, 0);
        final MealListItem mealLanch = new MealListItem(Meal.LAUNCH, 0);
        final MealListItem mealDinner = new MealListItem(Meal.DINNER, 0);

        final List<DishListItem> dishesBreakfast = new ArrayList<>();
        final List<DishListItem> dishesLanch = new ArrayList<>();
        final List<DishListItem> dishesDinner = new ArrayList<>();

        do {
            final String meal = c.getString(c.getColumnIndex(CName.MEAL));

            if (Objects.equals(meal, Meal.getValue(Meal.BREAKFAST))) {
                addToMealList(c, mealBreakfast, dishesBreakfast);
            } else if (Objects.equals(meal, Meal.getValue(Meal.LAUNCH))) {
                addToMealList(c, mealLanch, dishesLanch);
            } else if (Objects.equals(meal, Meal.getValue(Meal.DINNER))) {
                addToMealList(c, mealDinner, dishesDinner);
            }
        } while (c.moveToNext());

        //падает
        //c.close();

        addToDataSet(mealBreakfast, dishesBreakfast);
        addToDataSet(mealLanch, dishesLanch);
        addToDataSet(mealDinner, dishesDinner);*/

        this.dataSet = dataSet;

        notifyDataSetChanged();
    }

    /*private void addToMealList(Cursor c, MealListItem meal, List<DishListItem> dishes) {
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
    }*/
}
