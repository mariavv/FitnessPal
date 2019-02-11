package mariavv.fitnesspal.ui.journal.daycard;

import android.database.Cursor;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.util.ArrayList;
import java.util.Date;

import mariavv.fitnesspal.model.db.DbManager;
import mariavv.fitnesspal.model.model.Energy;
import mariavv.fitnesspal.model.model.MacroNutrients;
import mariavv.fitnesspal.model.repository.Repo;
import mariavv.fitnesspal.ui.journal.daycard.listviewtypes.HeaderListItem;

@InjectViewState
public class DayCardPresenter extends MvpPresenter<DayCardView> {

    void onGetDateArg(String date) {
        final Date d = new Date(date);
        final Cursor c = Repo.getInstance().getJournal(date);
        c.moveToFirst();

        ArrayList<ItemType> dataSet = new ArrayList<>();

        int mealNum = c.getInt(c.getColumnIndex(DbManager.JOURNAL_MEAL_NUM));
        /*while (c.getInt(c.getColumnIndex(DbManager.JOURNAL_MEAL_NUM)) == mealNum) {
            c.moveToNext();
        }*/
        HeaderListItem headerListItem = new HeaderListItem(new MacroNutrients(11, 22, 33), new Energy(1234));
        dataSet.add(headerListItem);

        getViewState().updateCard(dataSet);
    }
}
