package mariavv.fitnesspal.presentation.journal.daycard;

import android.database.Cursor;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.SingleStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

@StateStrategyType(SingleStateStrategy.class)
interface DayCardView extends MvpView {
    void updateCard(Cursor data);
}
