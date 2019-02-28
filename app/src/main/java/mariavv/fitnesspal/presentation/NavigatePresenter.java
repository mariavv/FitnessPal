package mariavv.fitnesspal.presentation;

import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.util.Calendar;
import java.util.Date;

import mariavv.fitnesspal.FitnessPal;
import mariavv.fitnesspal.data.db.Meal;
import mariavv.fitnesspal.data.repository.Repo;
import mariavv.fitnesspal.data.repository.SharedDataRepository;
import mariavv.fitnesspal.domain.Dish;
import mariavv.fitnesspal.domain.Food;
import mariavv.fitnesspal.other.Const;
import mariavv.fitnesspal.other.FrmFabric;
import ru.terrakok.cicerone.Router;

@InjectViewState
public class NavigatePresenter extends MvpPresenter<NavigateView> {

    private Router router;

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        router = FitnessPal.instance.getRouter();
        router.newRootScreen(Const.Screen.JOURNAL);

        if (!SharedDataRepository.isNotFirstRun()) {
            initDb();
            SharedDataRepository.saveNotFirstRun(true);
        }
    }

    private void initDb() {
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
        repo.insertDishInJournal(new Dish(date, Meal.BREAKFAST.getValue(), 1, 150));
        repo.insertDishInJournal(new Dish(date, Meal.BREAKFAST.getValue(), 2, 250));
        repo.insertDishInJournal(new Dish(date, Meal.LAUNCH.getValue(), 5, 250));
        calendar.set(Calendar.DAY_OF_MONTH, 21);
        date = calendar.getTime();
        repo.insertDishInJournal(new Dish(date, Meal.BREAKFAST.getValue(), 3, 310));
        calendar.set(Calendar.DAY_OF_MONTH, 22);
        date = calendar.getTime();
        repo.insertDishInJournal(new Dish(date, Meal.BREAKFAST.getValue(), 4, 50));
    }

    void onNavigationJournalSelected() {
        router.newRootScreen(Const.Screen.JOURNAL);
    }

    void onNavigationHandbookSelected() {
        router.newRootScreen(Const.Screen.HANDBOOK);
    }

    void onResume() {
        getViewState().setNavigator();
    }

    void onPause() {
        getViewState().removeNavigator();
    }

    void changeFragment(FragmentManager fragmentManager, @IdRes int containerId) {
        final String screenName = getFragmentName(fragmentManager, containerId);
        final boolean needShowMenu = screenName.equals(Const.Screen.HANDBOOK)
                || screenName.equals(Const.Screen.JOURNAL);

        if (needShowMenu) {
            getViewState().showBottomMenu();
            getViewState().showHomeAsUp(false);
        } else {
            getViewState().hideBottomMenu();
            getViewState().showHomeAsUp(true);
        }
    }

    @NonNull
    private String getFragmentName(FragmentManager fragmentManager, @IdRes int containerId) {
        final Fragment fragment = fragmentManager.findFragmentById(containerId);
        if (fragment instanceof FrmFabric.IFragment) {
            return ((FrmFabric.IFragment) fragment).getName();
        }
        return "";
    }
}
