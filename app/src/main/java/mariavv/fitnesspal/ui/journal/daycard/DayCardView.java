package mariavv.fitnesspal.ui.journal.daycard;

import com.arellomobile.mvp.MvpView;

import java.util.List;

interface DayCardView extends MvpView {
    void updateCard(List<ItemType> dataSet);
}
