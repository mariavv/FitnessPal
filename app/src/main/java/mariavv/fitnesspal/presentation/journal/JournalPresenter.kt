package mariavv.fitnesspal.presentation.journal

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import mariavv.fitnesspal.App
import mariavv.fitnesspal.R
import mariavv.fitnesspal.domain.interact.DbInteractor
import mariavv.fitnesspal.other.Const
import mariavv.fitnesspal.other.Utils.formatDate
import mariavv.fitnesspal.other.eventbus.AddDishEvent
import mariavv.fitnesspal.other.eventbus.Status
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

@InjectViewState
class JournalPresenter : MvpPresenter<JournalView>() {

    private var adapterPosition: Int = 0

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        viewState.setTitle(R.string.journal_title)

        viewState.setAdapterItems(DbInteractor().journalDates, 0)

        viewState.setDate(getDate(0))
        viewState.setPrevDayDisable()
        if (DbInteractor().journalDaysCount == 1) {
            viewState.setNextDayDisable()
        }
    }

    internal fun onFabClick() {
        App.getRouter().navigateTo(Const.Screen.ADD_DISH)
    }

    private fun moveToPosition(position: Int) {
        adapterPosition = position
        viewState.moveToPosition(position, getDate(position))
    }

    private fun getDate(position: Int): String {
        return formatDate(DbInteractor().getDateByIndex(position))
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

    fun onStart() {
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this)
        }
    }

    override fun onDestroy() {
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this)
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onEvent(event: AddDishEvent) {
        if (event.status == Status.SUCCESS) {
            viewState.setAdapterItems(DbInteractor().journalDates, adapterPosition)
            moveToPosition(adapterPosition)
            if (adapterPosition != 0) {
                viewState.setPrevDayDisable()
            } else {
                viewState.setPrevDayEnable()
            }
            if (adapterPosition + 1 == DbInteractor().journalDaysCount) {
                viewState.setNextDayDisable()
            } else {
                viewState.setNextDayEnable()
            }
        }
    }
}
