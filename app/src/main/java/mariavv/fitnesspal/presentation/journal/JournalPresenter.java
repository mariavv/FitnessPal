package mariavv.fitnesspal.presentation.journal;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import mariavv.fitnesspal.FitnessPal;
import mariavv.fitnesspal.data.repository.Repo;
import mariavv.fitnesspal.other.Const;
import ru.terrakok.cicerone.Router;

import static mariavv.fitnesspal.other.Utils.formatDate;

@InjectViewState
public class JournalPresenter extends MvpPresenter<JournalView> {

    private Router router;

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        router = FitnessPal.instance.getRouter();
    }

    void onFabClick() {
        router.navigateTo(Const.Screen.ENTER_SCREEN);
    }

    void onPageMove(int position) {
        setDate(position);
    }

    private void setDate(int position) {
        final long date = Repo.getInstance().getDateByIndex(position);

        getViewState().setDate(formatDate(date));
    }
}
