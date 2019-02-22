package mariavv.fitnesspal.presentation.adddish;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

@StateStrategyType(SkipStrategy.class)
interface AddDishView extends MvpView {

    @StateStrategyType(AddToEndSingleStrategy.class)
    void showSelectedDate(String date);

    void showDatePickerDialog();

    void initDatePickerDialog(int currentYear, int currentMonth, int currentDay);

    void configureFoodList(String[] foodList);

    void configureMealsSpinner(String[] meals);

    void showMessage(String message);
}
