package mariavv.fitnesspal.presentation.handbook;

import android.database.Cursor;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

@StateStrategyType(SkipStrategy.class)
interface HandBookView extends MvpView {
    void updateFeed(Cursor c);
}
