package mariavv.fitnesspal.ui.journal;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.util.Date;

import mariavv.fitnesspal.FitnessPal;
import mariavv.fitnesspal.model.model.Dish;
import mariavv.fitnesspal.model.repository.Repo;

@InjectViewState
public class JournalPresenter extends MvpPresenter<JournalView> {

    private Repo repo;

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();

        repo = FitnessPal.getRepo();

        repo.insertDishInJournal(new Dish(new Date(), 1, 150));
        repo.insertDishInJournal(new Dish(new Date(), 2, 250));

        //Cursor c = repo.getFoodsFromHandbook();

        //getViewState().updateData(c);
    }
}
