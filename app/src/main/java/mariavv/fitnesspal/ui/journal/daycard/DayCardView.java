package mariavv.fitnesspal.ui.journal.daycard;

import android.database.Cursor;

import com.arellomobile.mvp.MvpView;

interface DayCardView extends MvpView {
    void updateCard(String date, Cursor c);
}
