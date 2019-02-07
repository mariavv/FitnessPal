package mariavv.fitnesspal.ui.journal;

import android.database.Cursor;

import com.arellomobile.mvp.MvpView;

interface JournalView extends MvpView {
    void updateData(Cursor c);
}
