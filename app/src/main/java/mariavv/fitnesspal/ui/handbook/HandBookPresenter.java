package mariavv.fitnesspal.ui.handbook;

import android.content.Context;
import android.database.Cursor;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import mariavv.fitnesspal.model.entity.Food;
import mariavv.fitnesspal.model.repository.Repo;

@InjectViewState
public class HandBookPresenter extends MvpPresenter<HandBookView> {

    private Repo repo;

    void onCreateView(Context context) {
        repo = Repo.getInstance(context);

        repo.insertFoodInHandbook(new Food("Омлет", 15, 18, 3));
        repo.insertFoodInHandbook(new Food("Кофе с молоком", 1, 1, 3));
        repo.insertFoodInHandbook(new Food("Мясной салат", 5, 5, 10));
        repo.insertFoodInHandbook(new Food("Салат овощной", 1, 2, 5));
        repo.insertFoodInHandbook(new Food("Котлеты", 15, 14, 8));
        repo.insertFoodInHandbook(new Food("Пюре", 1, 13, 18));
        repo.insertFoodInHandbook(new Food("Сырники", 5, 15, 25));
        repo.insertFoodInHandbook(new Food("Малиновый чизкейк", 5, 15, 40));
        repo.insertFoodInHandbook(new Food("Макароны с сыром", 3, 9, 22));
        repo.insertFoodInHandbook(new Food("Гуляш", 18, 11, 2));
        Cursor c = repo.getFoodsFromHandbook();

        getViewState().updateFeed(c);
    }
}
