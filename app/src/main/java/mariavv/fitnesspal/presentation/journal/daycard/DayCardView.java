package mariavv.fitnesspal.presentation.journal.daycard;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.SingleStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

import java.util.List;

@StateStrategyType(SingleStateStrategy.class)
interface DayCardView extends MvpView {
    void updateCard(List<ItemType> dataSet, int dayProtein, int dayFat, int dayCarb, int dayEnergy);
}
