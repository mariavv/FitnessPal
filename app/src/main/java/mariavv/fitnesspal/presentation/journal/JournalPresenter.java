package mariavv.fitnesspal.presentation.journal;

import android.support.annotation.DrawableRes;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import mariavv.fitnesspal.FitnessPal;
import mariavv.fitnesspal.R;
import mariavv.fitnesspal.data.repository.Repo;
import mariavv.fitnesspal.other.Const;
import ru.terrakok.cicerone.Router;

import static mariavv.fitnesspal.other.Utils.formatDate;

@InjectViewState
public class JournalPresenter extends MvpPresenter<JournalView> {

    private Router router;

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        router = FitnessPal.instance.getRouter();
    }

    void onCreateView(int currentPage, int pageCount) {
        configureDayNavigateBtns(currentPage == 0,
                R.drawable.ic_chevron_left_black_inactive_24dp,
                currentPage == pageCount - 1,
                R.drawable.ic_chevron_right_black_inactive_24dp);
        /*if (currentPage == 0) {
            getViewState().setPrevDayImageDrawable(R.drawable.ic_chevron_left_black_inactive_24dp);
        } else if (currentPage == pageCount - 1) {
            getViewState().setNextDayImageDrawable(R.drawable.ic_chevron_right_black_inactive_24dp);
        }*/
    }

    void onFabClick() {
        router.navigateTo(Const.Screen.ENTER_SCREEN);
    }

    private void moveToPosition(int position) {
        final long date = Repo.getInstance().getDateByIndex(position);
        getViewState().moveToPosition(position, formatDate(date));

        //getViewState().setDate(formatDate(date));
    }

    void onPrevDayClick(int currentPage, int pageCount) {
        moveToPosition(currentPage - 1);

        configureDayNavigateBtns(currentPage == 1,
                R.drawable.ic_chevron_left_black_inactive_24dp,
                currentPage == pageCount - 1,
                R.drawable.ic_chevron_right_black_24dp);

        /*if (currentPage == 1) {
            getViewState().setPrevDayImageDrawable(R.drawable.ic_chevron_left_black_inactive_24dp);
        } else if (currentPage == pageCount - 1) {
            getViewState().setNextDayImageDrawable(R.drawable.ic_chevron_right_black_24dp);
        }*/
    }

    void onNextDayClick(int currentPage, int pageCount) {
        moveToPosition(currentPage + 1);

        configureDayNavigateBtns(currentPage == 1,
                R.drawable.ic_chevron_left_black_24dp,
                currentPage == pageCount - 2,
                R.drawable.ic_chevron_right_black_inactive_24dp);

        /*if (currentPage == 1) {
            getViewState().setPrevDayImageDrawable(R.drawable.ic_chevron_left_black_24dp);
        } else if (currentPage == pageCount - 2) {
            getViewState().setNextDayImageDrawable(R.drawable.ic_chevron_right_black_inactive_24dp);
        }*/
    }

    private void configureDayNavigateBtns(boolean prevCondition, @DrawableRes int drawResLeftBtn,
                                          boolean nextCondition, @DrawableRes int drawResRightBtn) {
        if (prevCondition) {
            getViewState().setPrevDayImageDrawable(drawResLeftBtn);
        } else if (nextCondition) {
            getViewState().setNextDayImageDrawable(drawResRightBtn);
        }
    }
}
