package mariavv.fitnesspal.presentation;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.view.MenuItem;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;

import java.util.Calendar;
import java.util.Date;

import mariavv.fitnesspal.FitnessPal;
import mariavv.fitnesspal.R;
import mariavv.fitnesspal.data.db.Meal;
import mariavv.fitnesspal.data.repository.Repo;
import mariavv.fitnesspal.domain.Dish;
import mariavv.fitnesspal.domain.Food;
import mariavv.fitnesspal.other.Const;
import mariavv.fitnesspal.presentation.adddish.AddDishFragment;
import mariavv.fitnesspal.presentation.addfood.AddFoodFragment;
import mariavv.fitnesspal.presentation.handbook.HandbookFragment;
import mariavv.fitnesspal.presentation.journal.JournalFragment;
import ru.terrakok.cicerone.Navigator;
import ru.terrakok.cicerone.android.SupportFragmentNavigator;

public class NavigateActivity extends MvpAppCompatActivity implements NavigateView {

    @InjectPresenter
    NavigatePresenter presenter;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_dashboard:
                    presenter.onNavigationJournalSelected();
                    return true;
                case R.id.navigation_notifications:
                    presenter.onNavigationHandbookSelected();
                    return true;
            }
            return false;
        }
    };

    private Navigator navigator = new SupportFragmentNavigator(getSupportFragmentManager(), R.id.main_menu_tabs_containier) {

        @Override
        protected Fragment createFragment(String screenKey, Object data) {
            switch (screenKey) {
                case Const.Screen.JOURNAL_SCREEN:
                    return new JournalFragment();
                case Const.Screen.HANDBOOK_SCREEN:
                    return new HandbookFragment();
                case Const.Screen.ENTER_SCREEN:
                    return new AddDishFragment();
                case Const.Screen.ADD_FOOD_SCREEN:
                    return new AddFoodFragment();
                default:
                    throw new RuntimeException("Unknown screen key!");
            }
        }

        @Override
        protected void showSystemMessage(String message) {
            Toast.makeText(NavigateActivity.this, message, Toast.LENGTH_SHORT).show();
        }

        @Override
        protected void exit() {
            finish();
        }
    };

    @Override
    protected void onResume() {
        super.onResume();
        presenter.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        presenter.onPause();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);

        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        //test data
        final Repo repo = Repo.getInstance();
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
        repo.insertFoodInHandbook(new Food("Творог 2%", 18, 2, 0));
        repo.insertFoodInHandbook(new Food("Фрукты", 1, 1, 10));
        repo.insertFoodInHandbook(new Food("Хлеб", 7, 3, 44));

        //test data
        repo.clearJournal();
        final Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.DAY_OF_MONTH, 20);
        calendar.set(Calendar.MONTH, Calendar.FEBRUARY);
        calendar.set(Calendar.YEAR, 2019);
        Date date = calendar.getTime();
        repo.insertDishInJournal(new Dish(date, Meal.BREAKFAST, 1, 150));
        repo.insertDishInJournal(new Dish(date, Meal.BREAKFAST, 2, 250));
        repo.insertDishInJournal(new Dish(date, Meal.LANCH, 5, 250));
        calendar.set(Calendar.DAY_OF_MONTH, 21);
        date = calendar.getTime();
        repo.insertDishInJournal(new Dish(date, Meal.BREAKFAST, 3, 310));
        calendar.set(Calendar.DAY_OF_MONTH, 22);
        date = calendar.getTime();
        repo.insertDishInJournal(new Dish(date, Meal.BREAKFAST, 4, 50));
    }

    @Override
    public void setNavigator() {
        FitnessPal.instance.getNavigatorHolder().setNavigator(navigator);
    }

    @Override
    public void removeNavigator() {
        FitnessPal.instance.getNavigatorHolder().removeNavigator();
    }

    //todo
    /*public void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }*/
}
