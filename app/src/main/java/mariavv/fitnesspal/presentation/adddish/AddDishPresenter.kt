package mariavv.fitnesspal.presentation.adddish

import android.database.Cursor
import android.text.Editable
import android.view.inputmethod.EditorInfo
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import mariavv.fitnesspal.App
import mariavv.fitnesspal.R
import mariavv.fitnesspal.data.db.Meal
import mariavv.fitnesspal.data.repository.Repo
import mariavv.fitnesspal.other.Utils
import java.util.*

@InjectViewState
class AddDishPresenter : MvpPresenter<AddDishView>() {
    private var date: Date = Date()
    private lateinit var foodList: Cursor
    private var selectedMealListPos: Int = 0
    private lateinit var meals: Array<String>


    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        viewState.setTitle(R.string.add_dish_title)

        val cal = GregorianCalendar()
        val cYear = cal.get(Calendar.YEAR)
        val cMonth = cal.get(Calendar.MONTH)
        val cDay = cal.get(Calendar.DAY_OF_MONTH)
        viewState.initDatePickerDialog(cYear, cMonth, cDay)
        onDateChange(cYear, cMonth, cDay)

        foodList = Repo.instance.foodList
        val fl = Array(foodList.count) { "" }
        foodList.moveToFirst()
        do {
            fl[foodList.position] = foodList.getString(0)
        } while (foodList.moveToNext())
        viewState.configureFoodList(fl)

        meals = Array(4) { "" }
        meals[0] = ""
        meals[1] = Meal.BREAKFAST.value
        meals[2] = Meal.LAUNCH.value
        meals[3] = Meal.DINNER.value

        viewState.configureMealsSpinner(meals)
    }

    internal fun onDateChange(year: Int, month: Int, dayOfMonth: Int) {
        date = Utils.getDate(year, month, dayOfMonth)
        viewState.showSelectedDate(Utils.formatDate(date.time))
    }

    internal fun onDateChangeClick() {
        viewState.showDatePickerDialog()
    }

    override fun onDestroy() {
        super.onDestroy()
        foodList.close()
    }

    internal fun onAddClick(food: Editable, weight: Editable) {
        if (weight.toString().isEmpty() || food.toString().isEmpty() || selectedMealListPos == 0) {
            App.getRouter().showSystemMessage(App.getAppString(R.string.some_empty_fields_message))
            return
        }

        val dishId = Repo.instance.addDish(
                date,
                meals[selectedMealListPos],
                food.toString(),
                Integer.valueOf(weight.toString()))

        if (dishId > -1) {
            App.getRouter().showSystemMessage(App.getAppString(R.string.add_message))
            viewState.clearFields()
        } else {
            App.getRouter().showSystemMessage(App.getAppString(R.string.add_dish_fail))
        }
    }

    internal fun onMealSelected(position: Int) {
        selectedMealListPos = position
    }

    internal fun onEditorAction(actionId: Int): Boolean {
        if (actionId == EditorInfo.IME_ACTION_GO) {
            viewState.addBtnCallOnClick()
        }
        return false
    }

    internal fun onBackPressed() {
        App.getRouter().exit()
    }
}
