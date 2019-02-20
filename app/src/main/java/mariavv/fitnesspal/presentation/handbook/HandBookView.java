package mariavv.fitnesspal.presentation.handbook;

import android.database.Cursor;

import com.arellomobile.mvp.MvpView;

interface HandBookView extends MvpView {
    void updateFeed(Cursor c);
}
