package mariavv.fitnesspal.ui.enter;

import android.database.Cursor;
import android.text.Editable;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.util.Calendar;
import java.util.Date;

import mariavv.fitnesspal.model.DateHelper;
import mariavv.fitnesspal.model.model.MealNum;
import mariavv.fitnesspal.model.repository.Repo;
import mariavv.fitnesspal.ui.UiTools;

import static mariavv.fitnesspal.model.DateHelper.getFormatDate;

@InjectViewState
public class EnterPresenter extends MvpPresenter<EnterView> {

    private Date date;
    private Cursor foodList;
    private int selectedFoodListPos;
    private int selectedMealListPos;

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();

        getViewState().initCalendarDialog(DateHelper.getCurrentYear(), DateHelper.getCurrentMonth(), DateHelper.getCurrentDay());
        onDateChange(DateHelper.getCurrentYear(), DateHelper.getCurrentMonth(), DateHelper.getCurrentDay());

        foodList = Repo.getInstance().getFoofList();
        final String[] fl = new String[foodList.getCount()];
        foodList.moveToFirst();
        do {
            fl[foodList.getPosition()] = foodList.getString(0);
        } while (foodList.moveToNext());
        getViewState().configureSpinner(fl);

        getViewState().configureMealsSpinner(MealNum.meals);
    }

    void onDateChange(int year, int month, int dayOfMonth) {
        final Calendar c = Calendar.getInstance();
        c.clear();
        c.set(year, month, dayOfMonth);
        date = c.getTime();
        getViewState().showSelectedDate(getFormatDate(date));
    }

    void onDateChangeClick() {
        getViewState().showDatePickerDialog();
    }

    void onFoodSelected(int position) {
        selectedFoodListPos = position;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        foodList.close();
    }

    void onAddClick(Editable food, Editable weight) {
        foodList.moveToPosition(selectedFoodListPos);
        Repo.getInstance().addDish(date, selectedMealListPos + 1,
                food.toString(),
                Integer.valueOf(weight.toString()));
        UiTools.showToast("добавлено");
    }

    void onMealSelected(int position) {
        selectedMealListPos = position;
    }
}
