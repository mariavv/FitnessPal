package mariavv.fitnesspal.ui.journal.daycard;

import android.database.Cursor;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.util.ArrayList;
import java.util.List;

import mariavv.fitnesspal.model.db.DbManager;
import mariavv.fitnesspal.model.model.FoodName;
import mariavv.fitnesspal.model.model.MacroNutrients;
import mariavv.fitnesspal.model.model.MealNum;
import mariavv.fitnesspal.model.model.Weight;
import mariavv.fitnesspal.model.repository.Repo;
import mariavv.fitnesspal.ui.journal.daycard.listviewtypes.DishListItem;
import mariavv.fitnesspal.ui.journal.daycard.listviewtypes.HeaderListItem;
import mariavv.fitnesspal.ui.journal.daycard.listviewtypes.MealListItem;

import static mariavv.fitnesspal.model.model.Energy.PER_WEIGHT;

@InjectViewState
public class DayCardPresenter extends MvpPresenter<DayCardView> {

    void onGetDateArg(String date) {
        final Cursor c = Repo.getInstance().getJournal(date);
        c.moveToFirst();

        final List<ItemType> dataSet = new ArrayList<>();

        HeaderListItem headerListItem = new HeaderListItem(new MacroNutrients(11, 22, 33));
        dataSet.add(headerListItem);

        do {

            MealListItem meal = new MealListItem(new MealNum(0), new MacroNutrients(0, 0, 0));

            meal.mealNum.value = c.getInt(c.getColumnIndex(DbManager.JOURNAL_MEAL_NUM));

            List<DishListItem> dishesList = new ArrayList<>();

            do {
                FoodName foodName = new FoodName(c.getString(c.getColumnIndex(DbManager.FOOD_NAME)));
                MacroNutrients macroNutrients = new MacroNutrients(c.getInt(c.getColumnIndex(DbManager.FOOD_PROTEIN)),
                        c.getInt(c.getColumnIndex(DbManager.FOOD_FAT)),
                        c.getInt(c.getColumnIndex(DbManager.FOOD_CARB)));
                Weight weight = new Weight(c.getInt(c.getColumnIndex(DbManager.JOURNAL_WEIGHT)));
                DishListItem dish = new DishListItem(foodName, macroNutrients, weight);

                dishesList.add(dish);
                //dataSet.add(dish);

                meal.macroNutrients.protein += macroNutrients.protein * weight.value / PER_WEIGHT;
                meal.macroNutrients.fat += macroNutrients.fat * weight.value / PER_WEIGHT;
                meal.macroNutrients.carb += macroNutrients.carb * weight.value / PER_WEIGHT;

            }
            while (c.moveToNext() && (c.getInt(c.getColumnIndex(DbManager.JOURNAL_MEAL_NUM)) == meal.mealNum.value));

            dataSet.add(meal);
            dataSet.addAll(dishesList);

            headerListItem.macroNutrients.protein += meal.macroNutrients.protein;
            headerListItem.macroNutrients.fat += meal.macroNutrients.fat;
            headerListItem.macroNutrients.carb += meal.macroNutrients.carb;

           /* while (c.getInt(c.getColumnIndex(DbManager.JOURNAL_MEAL_NUM)) == meal.mealNum.value) {
                dishCount++;

                c.moveToNext();
            }*/

        } while (c.moveToNext());

        c.close();

        getViewState().updateCard(dataSet);
    }
}
