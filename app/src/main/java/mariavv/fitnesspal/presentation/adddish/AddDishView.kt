package mariavv.fitnesspal.presentation.adddish

import android.support.annotation.StringRes
import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

@StateStrategyType(SkipStrategy::class)
interface AddDishView : MvpView {

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun showSelectedDate(date: String)

    fun showDatePickerDialog()
    fun initDatePickerDialog(currentYear: Int, currentMonth: Int, currentDay: Int)
    fun configureFoodList(foodList: Array<String>)
    fun configureMealsSpinner(meals: Array<String>)
    fun addBtnCallOnClick()
    fun setTitle(@StringRes title: Int)
    fun clearFields()
}
