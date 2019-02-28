package mariavv.fitnesspal.presentation.handbook;

import android.database.Cursor;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

@StateStrategyType(AddToEndSingleStrategy.class)
interface HandBookView extends MvpView {

    void updateFeed(Cursor c);

    void setTitle();
}
//import com.arellomobile.mvp.viewstate.strategy.SingleStateStrategy;
