package mariavv.fitnesspal.ui.journal;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.util.Calendar;
import java.util.Date;

import mariavv.fitnesspal.model.model.Dish;
import mariavv.fitnesspal.model.repository.Repo;

@InjectViewState
public class JournalPresenter extends MvpPresenter<JournalView> {

    private Repo repo;

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();

        repo = Repo.getInstance();

        //test
        repo.clearJournal();
        final Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.DAY_OF_MONTH, 8);
        calendar.set(Calendar.MONTH, Calendar.FEBRUARY);
        calendar.set(Calendar.YEAR, 2019);
        Date date = calendar.getTime();
        repo.insertDishInJournal(new Dish(date, 1, 150));
        repo.insertDishInJournal(new Dish(date, 2, 250));
        calendar.set(Calendar.DAY_OF_MONTH, 9);
        date = calendar.getTime();
        repo.insertDishInJournal(new Dish(date, 3, 310));
        calendar.set(Calendar.DAY_OF_MONTH, 10);
        date = calendar.getTime();
        repo.insertDishInJournal(new Dish(date, 4, 50));
    }
}
