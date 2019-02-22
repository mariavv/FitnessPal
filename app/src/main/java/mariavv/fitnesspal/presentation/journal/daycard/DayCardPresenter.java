package mariavv.fitnesspal.presentation.journal.daycard;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import mariavv.fitnesspal.data.repository.Repo;

@InjectViewState
public class DayCardPresenter extends MvpPresenter<DayCardView> {

    void onGetDateArg(long date) {
        getViewState().updateCard(Repo.getInstance().getJournal(date));
    }
}
