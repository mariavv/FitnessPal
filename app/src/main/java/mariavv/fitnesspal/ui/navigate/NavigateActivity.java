package mariavv.fitnesspal.ui.navigate;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import java.util.Calendar;
import java.util.Date;

import mariavv.fitnesspal.R;
import mariavv.fitnesspal.model.model.Dish;
import mariavv.fitnesspal.model.model.Food;
import mariavv.fitnesspal.model.model.FoodName;
import mariavv.fitnesspal.model.model.MacroNutrients;
import mariavv.fitnesspal.model.model.MealNum;
import mariavv.fitnesspal.model.model.Weight;
import mariavv.fitnesspal.model.repository.Repo;
import mariavv.fitnesspal.ui.UiTools;
import mariavv.fitnesspal.ui.handbook.HandBookFragment;
import mariavv.fitnesspal.ui.journal.JournalFragment;

public class NavigateActivity extends AppCompatActivity {

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_dashboard:
                    UiTools.replaceFragment(JournalFragment.newInstance(), getSupportFragmentManager());
                    return true;
                case R.id.navigation_notifications:
                    UiTools.replaceFragment(HandBookFragment.newInstance(), getSupportFragmentManager());
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);

        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        //test data
        final Repo repo = Repo.getInstance();
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


        //test data
        repo.clearJournal();
        final Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.DAY_OF_MONTH, 8);
        calendar.set(Calendar.MONTH, Calendar.FEBRUARY);
        calendar.set(Calendar.YEAR, 2019);
        Date date = calendar.getTime();
        repo.insertDishInJournal(new Dish(new MealNum(1), date, 1, new Weight(150)));
        repo.insertDishInJournal(new Dish(new MealNum(1), date, 2, new Weight(250)));
        repo.insertDishInJournal(new Dish(new MealNum(2), date, 5, new Weight(250)));
        calendar.set(Calendar.DAY_OF_MONTH, 9);
        date = calendar.getTime();
        repo.insertDishInJournal(new Dish(new MealNum(1), date, 3, new Weight(310)));
        calendar.set(Calendar.DAY_OF_MONTH, 10);
        date = calendar.getTime();
        repo.insertDishInJournal(new Dish(new MealNum(1), date, 4, new Weight(50)));

        UiTools.replaceFragment(JournalFragment.newInstance(), getSupportFragmentManager());
    }

}
