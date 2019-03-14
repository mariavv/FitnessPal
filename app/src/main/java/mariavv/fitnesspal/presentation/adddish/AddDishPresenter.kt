package mariavv.fitnesspal.presentation.adddish

import android.database.Cursor
import android.text.Editable
import android.util.Log
import android.view.inputmethod.EditorInfo
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import mariavv.fitnesspal.App
import mariavv.fitnesspal.R
import mariavv.fitnesspal.data.db.Meal
import mariavv.fitnesspal.data.repository.DbRepository
import mariavv.fitnesspal.domain.Dish
import mariavv.fitnesspal.other.Const
import mariavv.fitnesspal.other.Utils
import java.util.*

@InjectViewState
class AddDishPresenter : MvpPresenter<AddDishView>() {
    private var date: Date = Date()
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

        DbRepository.instance.foodNamesFromHandbook
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onGetFoodNames)
                .addTo(CompositeDisposable())
    }

    private fun onGetFoodNames(foodList: Cursor) {
        val fl = Array(foodList.count) { "" }
        foodList.moveToFirst()
        do {
            fl[foodList.position] = foodList.getString(0)
        } while (foodList.moveToNext())
        foodList.close()
        viewState.configureFoodList(fl)

        meals = Array(Meal.values().size + 1) { "" }
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

    internal fun onAddClick(food: Editable, weight: Editable) {
        if (weight.toString().isEmpty() || food.toString().isEmpty() || selectedMealListPos == 0) {
            App.getRouter().showSystemMessage(App.getAppString(R.string.some_empty_fields_message))
            return
        }

        DbRepository.instance.getFoodIdByName(food.toString())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { id -> onGetFoodId(id, weight.toString()) }
                .addTo(CompositeDisposable())
    }

    private fun onGetFoodId(id: Int, weight: String) {
        //val dishId = DbRepository.instance.insertDishInJournal(Dish(date, meals[selectedMealListPos],
        //        id, Integer.valueOf(weight)))

        Single.fromCallable {
            DbRepository.instance.insertDishInJournal(Dish(
                    date, meals[selectedMealListPos], id, Integer.valueOf(weight)
            ))
        }.subscribeOn(
                Schedulers.io()
        ).observeOn(
                AndroidSchedulers.mainThread()
        ).subscribeBy(
                onSuccess = { id ->
                    App.getRouter().exitWithMessage(App.getAppString(R.string.add_message))
                    if (id < 1) {
                        App.getRouter().showSystemMessage(App.getAppString(R.string.add_dish_fail))
                    }
                    Log.d(Const.LOG_TAG, "insert dish success, id = $id")
                },
                onError = { t ->
                    App.getRouter().showSystemMessage(App.getAppString(R.string.add_dish_fail))
                    Log.d(Const.LOG_TAG, "insert dish error: ${t.message}")
                }
        ).addTo(
                CompositeDisposable()
        )
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
