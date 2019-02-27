package mariavv.fitnesspal.presentation.handbook;

import android.database.Cursor;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.SingleStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

@StateStrategyType(SingleStateStrategy.class)
interface HandBookView extends MvpView {
    void updateFeed(Cursor c);
}
