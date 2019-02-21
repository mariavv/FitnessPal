package mariavv.fitnesspal.presentation.journal;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

import mariavv.fitnesspal.FitnessPal;
import mariavv.fitnesspal.R;
import mariavv.fitnesspal.data.repository.Repo;
import mariavv.fitnesspal.other.KeyConst;
import ru.terrakok.cicerone.Router;

@InjectViewState
public class JournalPresenter extends MvpPresenter<JournalView> {

    private Router router;

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        router = FitnessPal.instance.getRouter();
    }

    void onFabClick() {
        router.navigateTo(KeyConst.Screen.ENTER_SCREEN);
    }

    public void onPageMove(int position) {
        calcHeaderInfo(position);
    }

    private void calcHeaderInfo(int position) {
        final String date = Repo.getInstance().getDateByIndex(position);
        String toStr = "";
        try {
            final DateFormat to = new SimpleDateFormat(FitnessPal.getAppString(R.string.date_format_display), Locale.getDefault());
            final DateFormat from = new SimpleDateFormat(FitnessPal.getAppString(R.string.date_pattern), Locale.getDefault());
            toStr = to.format(from.parse(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //final Cursor c = Repo.getInstance().get
        getViewState().setHeaderInfo(toStr, 18);
    }
}
