package mariavv.fitnesspal.presentation.addfood;

import android.text.Editable;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import mariavv.fitnesspal.FitnessPal;
import mariavv.fitnesspal.data.repository.Repo;
import mariavv.fitnesspal.other.KeyConst;
import ru.terrakok.cicerone.Router;

@InjectViewState
public class AddFoodPresenter extends MvpPresenter<AddFoodView> {

    private Router router;

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        router = FitnessPal.instance.getRouter();
    }

    void onAddClick(Editable foodEdText, Editable proteinEdText, Editable fatEdText, Editable carbEdText) {
        if (proteinEdText.length() == 0 || fatEdText.length() == 0 || carbEdText.length() == 0) {
            return;
        }
        Repo.getInstance().addFood(foodEdText.toString(), proteinEdText.toString(), fatEdText.toString(), carbEdText.toString());

        router.navigateTo(KeyConst.Screen.HANDBOOK_SCREEN);
    }
}
