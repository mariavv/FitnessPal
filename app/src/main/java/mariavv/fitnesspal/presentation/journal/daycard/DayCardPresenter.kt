package mariavv.fitnesspal.presentation.journal.daycard

import android.database.Cursor
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import mariavv.fitnesspal.data.db.ColumnName
import mariavv.fitnesspal.data.db.Meal
import mariavv.fitnesspal.data.repository.DbRepository
import mariavv.fitnesspal.domain.Food
import mariavv.fitnesspal.presentation.journal.daycard.listviewtypes.DishListItem
import mariavv.fitnesspal.presentation.journal.daycard.listviewtypes.MealListItem
import java.util.*

@InjectViewState
class DayCardPresenter : MvpPresenter<DayCardView>() {

    internal fun onGetDateArg(date: Long) {
        DbRepository.instance.getJournal(date)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onGetJournal)
                .addTo(CompositeDisposable())
    }

    private fun onGetJournal(journal: Cursor) {
        if (journal.moveToFirst()) {

            //каша

            val dataSet = ArrayList<ItemType>()

            var dayProtein = 0
            var dayFat = 0
            var dayCarb = 0

            val mealBreakfast = MealListItem(Meal.BREAKFAST, 0)
            val mealLanch = MealListItem(Meal.LAUNCH, 0)
            val mealDinner = MealListItem(Meal.DINNER, 0)

            val dishesBreakfast = ArrayList<DishListItem>()
            val dishesLaunch = ArrayList<DishListItem>()
            val dishesDinner = ArrayList<DishListItem>()

            do {
                val meal = journal.getString(journal.getColumnIndex(ColumnName.MEAL))

                val protein = journal.getInt(journal.getColumnIndex(ColumnName.PROTEIN))
                val fat = journal.getInt(journal.getColumnIndex(ColumnName.FAT))
                val carb = journal.getInt(journal.getColumnIndex(ColumnName.CARB))

                val food = Food(
                        name = journal.getString(journal.getColumnIndex(ColumnName.NAME)),
                        protein = protein,
                        fat = fat,
                        carb = carb)

                val weight = journal.getInt(journal.getColumnIndex(ColumnName.WEIGHT))

                if (meal == Meal.BREAKFAST.value) {
                    addToMealList(food, weight, mealBreakfast, dishesBreakfast)
                } else if (meal == Meal.LAUNCH.value) {
                    addToMealList(food, weight, mealLanch, dishesLaunch)
                } else if (meal == Meal.DINNER.value) {
                    addToMealList(food, weight, mealDinner, dishesDinner)
                }

                dayProtein += Food.getMacronutrientInWeight(food.protein, weight)
                dayFat += Food.getMacronutrientInWeight(food.fat, weight)
                dayCarb += Food.getMacronutrientInWeight(food.carb, weight)
            } while (journal.moveToNext())

            addToDataSet(dataSet, mealBreakfast, dishesBreakfast)
            addToDataSet(dataSet, mealLanch, dishesLaunch)
            addToDataSet(dataSet, mealDinner, dishesDinner)

            val dayEnergy = mealBreakfast.energy + mealDinner.energy + mealLanch.energy

            viewState.updateCard(dataSet, dayProtein, dayFat, dayCarb, dayEnergy)
        }

        journal.close()
    }

    private fun addToMealList(food: Food, weight: Int, meal: MealListItem, dishes: MutableList<DishListItem>) {
        val dish = DishListItem(food, weight)

        dishes.add(dish)
        meal.energy += food.getEnergy(dish.weight)
    }

    private fun addToDataSet(dataSet: MutableList<ItemType>, meal: MealListItem, dishes: List<DishListItem>) {
        if (!dishes.isEmpty()) {
            dataSet.add(meal)
            dataSet.addAll(dishes)
        }
    }
}
