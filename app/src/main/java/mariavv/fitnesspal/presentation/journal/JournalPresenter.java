package mariavv.fitnesspal.presentation.journal;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import mariavv.fitnesspal.FitnessPal;
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

        getViewState().setTitle();

        getViewState().setAdapterItems(Repo.getInstance().getJournalDates());

        getViewState().setDate(getDate(0));
        getViewState().setPrevDayDisable();
        if (Repo.getInstance().getJournalDaysCount() == 1) {
            getViewState().setNextDayDisable();
        }
    }

    void onFabClick() {
        router.navigateTo(Const.Screen.ADD_DISH);
    }

    private void moveToPosition(int position) {
        getViewState().moveToPosition(position, getDate(position));
    }

    private String getDate(int position) {
        return formatDate(Repo.getInstance().getDateByIndex(position));
    }

    void onPrevDayClick(int currentPage, int pageCount) {
        moveToPosition(currentPage - 1);

        if (currentPage == 1) {
            getViewState().setPrevDayDisable();
        }
        if (currentPage == pageCount - 1) {
            getViewState().setNextDayEnable();
        }
    }

    void onNextDayClick(int currentPage, int pageCount) {
        moveToPosition(currentPage + 1);

        if (currentPage == 0) {
            getViewState().setPrevDayEnable();
        }
        if (currentPage == pageCount - 2) {
            getViewState().setNextDayDisable();
        }
    }

    public void onBackPressed() {
        router.exit();
    }
}
