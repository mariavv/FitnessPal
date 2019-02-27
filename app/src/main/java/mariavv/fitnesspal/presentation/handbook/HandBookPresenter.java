package mariavv.fitnesspal.presentation.handbook;

import android.database.Cursor;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import mariavv.fitnesspal.FitnessPal;
import mariavv.fitnesspal.data.repository.Repo;
import mariavv.fitnesspal.other.Const;
import ru.terrakok.cicerone.Router;

@InjectViewState
public class HandBookPresenter extends MvpPresenter<HandBookView> {

    private Router router;

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();

        router = FitnessPal.instance.getRouter();

        final Cursor c = Repo.getInstance().getFoodsFromHandbook();
        getViewState().updateFeed(c);
    }

    void onFabClick() {
        router.navigateTo(Const.Screen.ADD_FOOD);
    }

    public void onBackPressed() {
        router.exit();
    }
}
