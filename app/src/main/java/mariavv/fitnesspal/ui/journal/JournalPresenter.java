package mariavv.fitnesspal.ui.journal;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.util.Calendar;
import java.util.Date;

import mariavv.fitnesspal.model.model.Dish;
import mariavv.fitnesspal.model.model.MealNum;
import mariavv.fitnesspal.model.model.Weight;
import mariavv.fitnesspal.model.repository.Repo;

@InjectViewState
public class JournalPresenter extends MvpPresenter<JournalView> {

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();

        Repo repo = Repo.getInstance();

        //test
        repo.clearJournal();
        final Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.DAY_OF_MONTH, 8);
        calendar.set(Calendar.MONTH, Calendar.FEBRUARY);
        calendar.set(Calendar.YEAR, 2019);
        Date date = calendar.getTime();
        repo.insertDishInJournal(new Dish(new MealNum(1), date, 1, new Weight(150)));
        repo.insertDishInJournal(new Dish(new MealNum(1), date, 2, new Weight(250)));
        repo.insertDishInJournal(new Dish(new MealNum(2), date, 5, new Weight(250)));
        calendar.set(Calendar.DAY_OF_MONTH, 9);
        date = calendar.getTime();
        repo.insertDishInJournal(new Dish(new MealNum(1), date, 3, new Weight(310)));
        calendar.set(Calendar.DAY_OF_MONTH, 10);
        date = calendar.getTime();
        repo.insertDishInJournal(new Dish(new MealNum(1), date, 4, new Weight(50)));
    }
}
