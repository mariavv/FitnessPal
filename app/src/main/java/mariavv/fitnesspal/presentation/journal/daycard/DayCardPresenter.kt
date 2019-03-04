package mariavv.fitnesspal.presentation.journal.daycard

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import mariavv.fitnesspal.data.db.CName
import mariavv.fitnesspal.data.db.Meal
import mariavv.fitnesspal.data.repository.Repo
import mariavv.fitnesspal.domain.Food
import mariavv.fitnesspal.presentation.journal.daycard.listviewtypes.DishListItem
import mariavv.fitnesspal.presentation.journal.daycard.listviewtypes.MealListItem
import java.util.*

@InjectViewState
class DayCardPresenter : MvpPresenter<DayCardView>() {

    internal fun onGetDateArg(date: Long) {
        val data = Repo.instance.getJournal(date)

        val dataSet = ArrayList<ItemType>()
        data.moveToFirst()

        //каша

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
            val meal = data.getString(data.getColumnIndex(CName.MEAL))

            val protein = data.getInt(data.getColumnIndex(CName.PROTEIN))
            val fat = data.getInt(data.getColumnIndex(CName.FAT))
            val carb = data.getInt(data.getColumnIndex(CName.CARB))

            val food = Food(data.getString(data.getColumnIndex(CName.NAME)), protein, fat, carb)

            val weight = data.getInt(data.getColumnIndex(CName.WEIGHT))

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
        } while (data.moveToNext())

        //падает
        //c.close();

        addToDataSet(dataSet, mealBreakfast, dishesBreakfast)
        addToDataSet(dataSet, mealLanch, dishesLaunch)
        addToDataSet(dataSet, mealDinner, dishesDinner)

        val dayEnergy = mealBreakfast.energy + mealDinner.energy + mealLanch.energy

        viewState.updateCard(dataSet, dayProtein, dayFat, dayCarb, dayEnergy)
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
