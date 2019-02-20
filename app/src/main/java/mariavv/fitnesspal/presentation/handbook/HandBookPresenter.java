package mariavv.fitnesspal.presentation.handbook;

import android.database.Cursor;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import mariavv.fitnesspal.data.repository.Repo;

@InjectViewState
public class HandBookPresenter extends MvpPresenter<HandBookView> {

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();

        final Cursor c = Repo.getInstance().getFoodsFromHandbook();

        getViewState().updateFeed(c);
    }
}
