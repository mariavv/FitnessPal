package mariavv.fitnesspal.presentation.journal

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import mariavv.fitnesspal.App
import mariavv.fitnesspal.R
import mariavv.fitnesspal.domain.interact.DbInteractor
import mariavv.fitnesspal.other.Const
import mariavv.fitnesspal.other.Utils.formatDate

@InjectViewState
class JournalPresenter : MvpPresenter<JournalView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        viewState.setTitle(R.string.journal_title)

        viewState.setAdapterItems(DbInteractor.instance.journalDates)

        viewState.setDate(getDate(0))
        viewState.setPrevDayDisable()
        if (DbInteractor.instance.journalDaysCount == 1) {
            viewState.setNextDayDisable()
        }
    }

    internal fun onFabClick() {
        App.getRouter().navigateTo(Const.Screen.ADD_DISH)
    }

    private fun moveToPosition(position: Int) {
        viewState.moveToPosition(position, getDate(position))
    }

    private fun getDate(position: Int): String {
        return formatDate(DbInteractor.instance.getDateByIndex(position))
    }

    internal fun onPrevDayClick(currentPage: Int, pageCount: Int) {
        if (currentPage < 1) {
            return
        }

        moveToPosition(currentPage - 1)

        if (currentPage == 1) {
            viewState.setPrevDayDisable()
        }
        if (currentPage == pageCount - 1) {
            viewState.setNextDayEnable()
        }
    }

    internal fun onNextDayClick(currentPage: Int, pageCount: Int) {
        if (currentPage + 1 >= pageCount) {
            return
        }

        moveToPosition(currentPage + 1)

        if (currentPage == 0) {
            viewState.setPrevDayEnable()
        }
        if (currentPage == pageCount - 2) {
            viewState.setNextDayDisable()
        }
    }

    fun onBackPressed() {
        App.getRouter().exit()
    }
}
