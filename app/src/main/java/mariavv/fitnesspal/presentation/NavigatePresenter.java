package mariavv.fitnesspal.presentation;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import mariavv.fitnesspal.FitnessPal;
import mariavv.fitnesspal.other.Const;
import ru.terrakok.cicerone.Router;

@InjectViewState
public class NavigatePresenter extends MvpPresenter<NavigateView> {

    private Router router;

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        router = FitnessPal.instance.getRouter();
        router.newRootScreen(Const.Screen.JOURNAL_SCREEN);
    }

    void onNavigationJournalSelected() {
        router.newRootScreen(Const.Screen.JOURNAL_SCREEN);
    }

    void onNavigationHandbookSelected() {
        router.newRootScreen(Const.Screen.HANDBOOK_SCREEN);
    }

    void onResume() {
        getViewState().setNavigator();
    }

    void onPause() {
        getViewState().removeNavigator();
    }
}
