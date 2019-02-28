package mariavv.fitnesspal.presentation.addfood;

import android.text.Editable;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import mariavv.fitnesspal.FitnessPal;
import mariavv.fitnesspal.R;
import mariavv.fitnesspal.data.repository.Repo;
import mariavv.fitnesspal.domain.Food;
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

        final int protein = Integer.valueOf(proteinEdText.toString());
        final int fat = Integer.valueOf(fatEdText.toString());
        final int carb = Integer.valueOf(carbEdText.toString());

        if (protein + fat + carb > 100) {
            router.showSystemMessage(FitnessPal.getAppString(R.string.error_pfc_summ_message));
            return;
        }

        final Food food = new Food(foodEdText.toString(), protein, fat, carb);
        if (Repo.getInstance().addFood(food) > -1) {
            router.exitWithMessage(FitnessPal.getAppString(R.string.add_message));
        } else {
            router.showSystemMessage(FitnessPal.getAppString(R.string.add_fail_message));
        }
    }

    void onBackPressed() {
        router.exit();
    }
}
