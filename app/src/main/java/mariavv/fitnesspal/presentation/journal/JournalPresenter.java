package mariavv.fitnesspal.presentation.journal;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import mariavv.fitnesspal.FitnessPal;
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
        calcHeaderInfo();
    }

    private void calcHeaderInfo() {
        getViewState().setHeaderInfo(18);
    }
}
