package mariavv.fitnesspal.presentation.journal;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

@StateStrategyType(AddToEndSingleStrategy.class)
interface JournalView extends MvpView {

    void setDate(String date);

    void moveToPosition(int i, String date);

    void setAdapterItems(long[] journalDates);

    void setPrevDayEnable();

    void setNextDayEnable();

    void setPrevDayDisable();

    void setNextDayDisable();

    void setTitle();
}
