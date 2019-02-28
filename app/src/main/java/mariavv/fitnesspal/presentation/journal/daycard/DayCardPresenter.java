package mariavv.fitnesspal.presentation.journal.daycard;

import android.database.Cursor;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import mariavv.fitnesspal.data.db.CName;
import mariavv.fitnesspal.data.db.Meal;
import mariavv.fitnesspal.data.repository.Repo;
import mariavv.fitnesspal.domain.Food;
import mariavv.fitnesspal.presentation.journal.daycard.listviewtypes.DishListItem;
import mariavv.fitnesspal.presentation.journal.daycard.listviewtypes.MealListItem;

@InjectViewState
public class DayCardPresenter extends MvpPresenter<DayCardView> {

    void onGetDateArg(long date) {
        final Cursor data = Repo.getInstance().getJournal(date);

        List<ItemType> dataSet = new ArrayList<>();
        data.moveToFirst();

        //каша

        int dayProtein = 0;
        int dayFat = 0;
        int dayCarb = 0;

        final MealListItem mealBreakfast = new MealListItem(Meal.BREAKFAST, 0);
        final MealListItem mealLanch = new MealListItem(Meal.LAUNCH, 0);
        final MealListItem mealDinner = new MealListItem(Meal.DINNER, 0);

        final List<DishListItem> dishesBreakfast = new ArrayList<>();
        final List<DishListItem> dishesLanch = new ArrayList<>();
        final List<DishListItem> dishesDinner = new ArrayList<>();

        do {
            final String meal = data.getString(data.getColumnIndex(CName.MEAL));

            final int protein = data.getInt(data.getColumnIndex(CName.PROTEIN));
            final int fat = data.getInt(data.getColumnIndex(CName.FAT));
            final int carb = data.getInt(data.getColumnIndex(CName.CARB));

            final Food food = new Food(data.getString(data.getColumnIndex(CName.NAME)), protein, fat, carb);

            final int weight = data.getInt(data.getColumnIndex(CName.WEIGHT));

            if (Objects.equals(meal, Meal.BREAKFAST.getValue())) {
                addToMealList(food, weight, mealBreakfast, dishesBreakfast);
            } else if (Objects.equals(meal, Meal.LAUNCH.getValue())) {
                addToMealList(food, weight, mealLanch, dishesLanch);
            } else if (Objects.equals(meal, Meal.DINNER.getValue())) {
                addToMealList(food, weight, mealDinner, dishesDinner);
            }

            dayProtein += food.getCount(food.getProtein(), weight);
            dayFat += food.getCount(food.getFat(), weight);
            dayCarb += food.getCount(food.getCarb(), weight);
        } while (data.moveToNext());

        //падает
        //c.close();

        addToDataSet(dataSet, mealBreakfast, dishesBreakfast);
        addToDataSet(dataSet, mealLanch, dishesLanch);
        addToDataSet(dataSet, mealDinner, dishesDinner);

        final int dayEnergy = mealBreakfast.energy + mealDinner.energy + mealLanch.energy;

        getViewState().updateCard(dataSet, dayProtein, dayFat, dayCarb, dayEnergy);
    }

    private void addToMealList(Food food, int weight, MealListItem meal, List<DishListItem> dishes) {
        final DishListItem dish = new DishListItem(food, weight);

        dishes.add(dish);
        meal.energy += food.getEnergy(dish.weight);
    }

    private void addToDataSet(List<ItemType> dataSet, MealListItem meal, List<DishListItem> dishes) {
        if (!dishes.isEmpty()) {
            dataSet.add(meal);
            dataSet.addAll(dishes);
        }
    }
}
