package mariavv.fitnesspal.presentation;

import android.annotation.SuppressLint;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import mariavv.fitnesspal.FitnessPal;
import mariavv.fitnesspal.data.repository.AssetsRepository;
import mariavv.fitnesspal.data.repository.Repo;
import mariavv.fitnesspal.data.repository.SharedDataRepository;
import mariavv.fitnesspal.domain.Dish;
import mariavv.fitnesspal.domain.Dish2;
import mariavv.fitnesspal.domain.Food;
import mariavv.fitnesspal.domain.TestData;
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

        if (!SharedDataRepository.isNotFirstRun()) {
            initDb();
            SharedDataRepository.saveNotFirstRun(true);
        } else {
            router.newRootScreen(Const.Screen.JOURNAL);
        }
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

    @SuppressLint("CheckResult")
    private void initDb() {
        //test data
        AssetsRepository repo = new AssetsRepository();

        //todo map
        Observable.fromCallable(repo.getTestData(FitnessPal.instance.getAssets()))
                .map(new Function<TestData, TestData>() {
                    @Override
                    public TestData apply(TestData testData) throws Exception {
                        return testData;
                    }
                }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onGetTestData);
    }

    private void onGetTestData(TestData data) {
        final Repo repo = Repo.getInstance();

        for (Food food : data.foods) {
            repo.insertFoodInHandbook(food);
        }

        //todo dish2
        for (Dish2 dish : data.dishes) {
            //todo
            SimpleDateFormat format = new SimpleDateFormat();
            format.applyPattern("dd.MM.yyyy");
            Date docDate = new Date();
            try {
                docDate = format.parse(dish.date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            repo.insertDishInJournal(new Dish(docDate, dish.meal, dish.foodId, dish.weight));
        }

        router.newRootScreen(Const.Screen.JOURNAL);
    }
}
