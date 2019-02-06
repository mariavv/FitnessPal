package mariavv.fitnesspal.ui.journal.daycard;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import mariavv.fitnesspal.model.repository.Repo;

@InjectViewState
public class DayCardPresenter extends MvpPresenter<DayCardView> {

    private Repo repo;

    void onGetDateArg(String date) {
        getViewState().updateCard();
    }
}
