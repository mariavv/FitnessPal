package mariavv.fitnesspal.presentation.journal.daycard

import android.database.Cursor
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import mariavv.fitnesspal.data.db.CName
import mariavv.fitnesspal.data.db.Meal
import mariavv.fitnesspal.data.repository.DbRepository
import mariavv.fitnesspal.domain.Food
import mariavv.fitnesspal.presentation.journal.daycard.listviewtypes.DishListItem
import mariavv.fitnesspal.presentation.journal.daycard.listviewtypes.MealListItem
import java.util.*

@InjectViewState
class DayCardPresenter : MvpPresenter<DayCardView>() {

    internal fun onGetDateArg(date: Long) {
        //val data = DbRepository.instance.getJournal(date)
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
                val meal = journal.getString(journal.getColumnIndex(CName.MEAL))

                val protein = journal.getInt(journal.getColumnIndex(CName.PROTEIN))
                val fat = journal.getInt(journal.getColumnIndex(CName.FAT))
                val carb = journal.getInt(journal.getColumnIndex(CName.CARB))

                val food = Food(journal.getString(journal.getColumnIndex(CName.NAME)), protein, fat, carb)

                val weight = journal.getInt(journal.getColumnIndex(CName.WEIGHT))

                if (meal == Meal.BREAKFAST.value) {
                    addToMealList(food, weight, mealBreakfast, dishesBreakfast)
                } else if (meal == Meal.LAUNCH.value) {
                    addToMealList(food, weight, mealLanch, dishesLaunch)
                } else if (meal == Meal.DINNER.value) {
                    addToMealList(food, weight, mealDinner, dishesDinner)
                }

                dayProtein += food.getCount(food.protein, weight)
                dayFat += food.getCount(food.fat, weight)
                dayCarb += food.getCount(food.carb, weight)
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
