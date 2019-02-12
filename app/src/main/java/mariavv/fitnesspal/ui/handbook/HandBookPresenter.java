package mariavv.fitnesspal.ui.handbook;

import android.database.Cursor;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import mariavv.fitnesspal.model.model.Food;
import mariavv.fitnesspal.model.model.FoodName;
import mariavv.fitnesspal.model.model.MacroNutrients;
import mariavv.fitnesspal.model.repository.Repo;

@InjectViewState
public class HandBookPresenter extends MvpPresenter<HandBookView> {

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();

        final Repo repo = Repo.getInstance();

        //test data
        repo.insertFoodInHandbook(new Food(new FoodName("Омлет"), new MacroNutrients(15, 18, 3)));
        repo.insertFoodInHandbook(new Food(new FoodName("Кофе с молоком"), new MacroNutrients(1, 1, 3)));
        repo.insertFoodInHandbook(new Food(new FoodName("Мясной салат"), new MacroNutrients(5, 5, 10)));
        repo.insertFoodInHandbook(new Food(new FoodName("Салат овощной"), new MacroNutrients(1, 2, 5)));
        repo.insertFoodInHandbook(new Food(new FoodName("Котлеты"), new MacroNutrients(15, 14, 8)));
        repo.insertFoodInHandbook(new Food(new FoodName("Пюре"), new MacroNutrients(1, 13, 18)));
        repo.insertFoodInHandbook(new Food(new FoodName("Сырники"), new MacroNutrients(5, 15, 25)));
        repo.insertFoodInHandbook(new Food(new FoodName("Малиновый чизкейк"), new MacroNutrients(5, 15, 40)));
        repo.insertFoodInHandbook(new Food(new FoodName("Макароны с сыром"), new MacroNutrients(3, 9, 22)));
        repo.insertFoodInHandbook(new Food(new FoodName("Гуляш"), new MacroNutrients(18, 11, 2)));
        repo.insertFoodInHandbook(new Food(new FoodName("Творог 2%"), new MacroNutrients(18, 2, 0)));
        repo.insertFoodInHandbook(new Food(new FoodName("Фрукты"), new MacroNutrients(1, 1, 10)));
        repo.insertFoodInHandbook(new Food(new FoodName("Хлеб"), new MacroNutrients(7, 3, 44)));

        final Cursor c = repo.getFoodsFromHandbook();

        getViewState().updateFeed(c);
    }
}
