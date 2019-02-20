package mariavv.fitnesspal.presentation.enter;

import android.database.Cursor;
import android.text.Editable;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import mariavv.fitnesspal.data.db.Meal;
import mariavv.fitnesspal.data.repository.Repo;

@InjectViewState
public class EnterPresenter extends MvpPresenter<EnterView> {

    private Date date;
    private Cursor foodList;
    private int selectedMealListPos;
    private String[] meals;

    private static int getCurrent(int mark) {
        return new GregorianCalendar().get(mark);
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();

        final int cYear = getCurrent(Calendar.YEAR);
        final int cMonth = getCurrent(Calendar.MONTH);
        final int cDay = getCurrent(Calendar.DAY_OF_MONTH);
        getViewState().initCalendarDialog(cYear, cMonth, cDay);
        onDateChange(cYear, cMonth, cDay);

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
        final String pattern = "dd-MM-yyyy";
        final SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern, Locale.getDefault());

        getViewState().showSelectedDate(simpleDateFormat.format(date));
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
