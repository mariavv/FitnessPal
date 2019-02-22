package mariavv.fitnesspal.presentation.addfood;

import android.text.Editable;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import mariavv.fitnesspal.FitnessPal;
import mariavv.fitnesspal.R;
import mariavv.fitnesspal.data.repository.Repo;
import mariavv.fitnesspal.other.Const;
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
            router.showSystemMessage(FitnessPal.getAppString(R.string.some_empty_fields_message));
            return;
        }

        if (Repo.getInstance().addFood(foodEdText.toString(), proteinEdText.toString(), fatEdText.toString(), carbEdText.toString()) > -1) {
            router.showSystemMessage(FitnessPal.getAppString(R.string.add_message));
            router.navigateTo(Const.Screen.HANDBOOK_SCREEN);
        } else {
            router.showSystemMessage(FitnessPal.getAppString(R.string.add_fail_message));
        }
    }
}
