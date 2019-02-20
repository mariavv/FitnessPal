package mariavv.fitnesspal.presentation.journal.daycard;

import android.database.Cursor;

import com.arellomobile.mvp.MvpView;

interface DayCardView extends MvpView {
    void updateCard(Cursor data);
}
