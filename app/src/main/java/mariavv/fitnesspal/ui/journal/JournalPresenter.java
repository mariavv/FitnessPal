package mariavv.fitnesspal.ui.journal;

import android.content.Context;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.util.Date;

import mariavv.fitnesspal.model.entity.Dish;
import mariavv.fitnesspal.model.repository.Repo;

@InjectViewState
public class JournalPresenter extends MvpPresenter<JournalView> {

    private Repo repo;

    void onCreateView(Context context) {
        repo = Repo.getInstance(context);

        repo.insertDishInJournal(new Dish(new Date(), 1, 150));
        repo.insertDishInJournal(new Dish(new Date(), 2, 250));

        //Cursor c = repo.getFoodsFromHandbook();

        //getViewState().updateData(c);
    }
}
