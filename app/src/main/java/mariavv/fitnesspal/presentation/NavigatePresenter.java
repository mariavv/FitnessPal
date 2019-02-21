package mariavv.fitnesspal.presentation;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import mariavv.fitnesspal.FitnessPal;
import mariavv.fitnesspal.other.KeyConst;
import ru.terrakok.cicerone.Router;

@InjectViewState
public class NavigatePresenter extends MvpPresenter<NavigateView> {

    private Router router;

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        router = FitnessPal.instance.getRouter();
        navigateTo(KeyConst.Screen.JOURNAL_SCREEN);
    }

    void onNavigationJournalSelected() {
        navigateTo(KeyConst.Screen.JOURNAL_SCREEN);
    }

    void onNavigationHandbookSelected() {
        navigateTo(KeyConst.Screen.HANDBOOK_SCREEN);
    }

    private void navigateTo(String screenKey) {
        router.navigateTo(screenKey);
    }
}
