package mariavv.fitnesspal.presentation.enter;

import android.database.Cursor;
import android.text.Editable;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.util.Calendar;
import java.util.Date;

import mariavv.fitnesspal.data.DateHelper;
import mariavv.fitnesspal.data.db.Meal;
import mariavv.fitnesspal.data.repository.Repo;

import static mariavv.fitnesspal.data.DateHelper.getFormatDate;

@InjectViewState
public class EnterPresenter extends MvpPresenter<EnterView> {

    private Date date;
    private Cursor foodList;
    private int selectedMealListPos;
    private String[] meals;

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

        meals = new String[4];
        meals[0] = "";
        meals[1] = Meal.BREAKFAST;
        meals[2] = Meal.LANCH;
        meals[3] = Meal.DINNER;

        getViewState().configureMealsSpinner(meals);
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

    @Override
    public void onDestroy() {
        super.onDestroy();
        foodList.close();
    }

    void onAddClick(Editable food, Editable weight) {
        if (weight.toString().length() == 0) {
            return;
        }
        if (food.toString().length() == 0) {
            return;
        }
        if (selectedMealListPos == 0) {
            return;
        }

        Repo.getInstance()
                .addDish(date, meals[selectedMealListPos], food.toString(), Integer.valueOf(weight.toString()));
        getViewState().showMessage("добавлено");
    }

    void onMealSelected(int position) {
        selectedMealListPos = position;
    }
}
