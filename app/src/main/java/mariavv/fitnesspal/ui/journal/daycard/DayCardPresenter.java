package mariavv.fitnesspal.ui.journal.daycard;

import android.database.Cursor;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.util.Date;

import mariavv.fitnesspal.model.repository.Repo;

@InjectViewState
public class DayCardPresenter extends MvpPresenter<DayCardView> {

    void onGetDateArg(String date) {
        final Date d = new Date(date);
        final Cursor c = Repo.getInstance().getJournal(date);
        getViewState().updateCard(date, c);
    }
}
