package mariavv.fitnesspal.presentation;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

@StateStrategyType(SkipStrategy.class)
public interface NavigateView extends MvpView {
    void setNavigator();

    void removeNavigator();

    void hideBottomMenu();

    void showBottomMenu();
}
