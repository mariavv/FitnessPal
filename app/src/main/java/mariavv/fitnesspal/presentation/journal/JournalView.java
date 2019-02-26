package mariavv.fitnesspal.presentation.journal;

import android.support.annotation.DrawableRes;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

@StateStrategyType(AddToEndSingleStrategy.class)
interface JournalView extends MvpView {

    //@StateStrategyType(AddToEndSingleStrategy.class)
    //void setDate(String toStr);

    void moveToPosition(int i, String date);

    void setPrevDayImageDrawable(@DrawableRes int imageRes);

    void setNextDayImageDrawable(@DrawableRes int imageRes);
}
