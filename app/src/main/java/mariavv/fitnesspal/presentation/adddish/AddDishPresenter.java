package mariavv.fitnesspal.presentation.adddish;

import android.database.Cursor;
import android.text.Editable;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import mariavv.fitnesspal.FitnessPal;
import mariavv.fitnesspal.R;
import mariavv.fitnesspal.data.db.Meal;
import mariavv.fitnesspal.data.repository.Repo;
import mariavv.fitnesspal.other.Utils;
import ru.terrakok.cicerone.Router;

@InjectViewState
public class AddDishPresenter extends MvpPresenter<AddDishView> {

    private Router router;

    private Date date;
    private Cursor foodList;
    private int selectedMealListPos;
    private String[] meals;


    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();

        router = FitnessPal.instance.getRouter();

        final Calendar cal = new GregorianCalendar();
        final int cYear = cal.get(Calendar.YEAR);
        final int cMonth = cal.get(Calendar.MONTH);
        final int cDay = cal.get(Calendar.DAY_OF_MONTH);
        getViewState().initDatePickerDialog(cYear, cMonth, cDay);
        onDateChange(cYear, cMonth, cDay);

        foodList = Repo.getInstance().getFoodList();
        final String[] fl = new String[foodList.getCount()];
        foodList.moveToFirst();
        do {
            fl[foodList.getPosition()] = foodList.getString(0);
        } while (foodList.moveToNext());
        getViewState().configureFoodList(fl);

        meals = new String[4];
        meals[0] = "";
        meals[1] = Meal.BREAKFAST;
        meals[2] = Meal.LANCH;
        meals[3] = Meal.DINNER;

        getViewState().configureMealsSpinner(meals);
    }

    void onDateChange(int year, int month, int dayOfMonth) {
        date = Utils.getDate(year, month, dayOfMonth);
        getViewState().showSelectedDate(Utils.formatDate(date.toString()));
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
        if (weight.toString().length() == 0 || food.toString().length() == 0 || selectedMealListPos == 0) {
            router.showSystemMessage(FitnessPal.getAppString(R.string.some_empty_fields_message));
            return;
        }

        if (Repo.getInstance().addDish(
                date,
                meals[selectedMealListPos],
                food.toString(),
                Integer.valueOf(weight.toString())) > -1) {
            router.showSystemMessage(FitnessPal.getAppString(R.string.add_message));
        } else {
            router.showSystemMessage(FitnessPal.getAppString(R.string.add_dish_fail));
        }
    }

    void onMealSelected(int position) {
        selectedMealListPos = position;
    }
}
