package mariavv.fitnesspal.ui.enter;

import com.arellomobile.mvp.MvpView;

interface EnterView extends MvpView {
    void showSelectedDate(String date);

    void showDatePickerDialog();

    void initCalendarDialog(int currentYear, int currentMonth, int currentDay);

    void configureSpinner(String[] foodList);
}
