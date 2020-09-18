package mariavv.fitnesspal.ui.enter;

import android.database.Cursor;
import android.text.Editable;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.util.Calendar;
import java.util.Date;

import mariavv.fitnesspal.model.DateHelper;
import mariavv.fitnesspal.model.repository.Repo;

import static mariavv.fitnesspal.model.DateHelper.getFormatDate;


@InjectViewState
public class EnterPresenter extends MvpPresenter<EnterView> {

    private Date date;
    private Cursor foodList;
    private int selectedFoodListPos;

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

    public void onFoodSelected(int position) {
        selectedFoodListPos = position;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        foodList.close();
    }

    public void onAddClick(Editable mealNum, Editable weight) {
        int f = 5;
        foodList.moveToFirst();
        Repo.getInstance().addDish(date, Integer.valueOf(mealNum.toString()),
                foodList.getString(selectedFoodListPos),
                Integer.valueOf(weight.toString()));
        f++;
    }
}
