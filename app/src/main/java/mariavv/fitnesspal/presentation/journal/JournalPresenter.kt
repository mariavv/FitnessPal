package mariavv.fitnesspal.presentation.journal

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import mariavv.fitnesspal.App
import mariavv.fitnesspal.R
import mariavv.fitnesspal.data.repository.DbRepository
import mariavv.fitnesspal.other.Const
import mariavv.fitnesspal.other.Utils.formatDate
import mariavv.fitnesspal.other.eventbus.AddDishEvent
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

@InjectViewState
class JournalPresenter : MvpPresenter<JournalView>() {

    private var adapterPosition: Int = 0

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        viewState.setTitle(R.string.journal_title)

        setJournalDates()

        DbRepository.instance.getDateByIndex(0)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { viewState::setDate }
                .addTo(CompositeDisposable())
        //viewState.setDate(getDate(0))
        viewState.setPrevDayDisable()

        DbRepository.instance.journalDaysCount()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::firstViewAttachOnGetJournalDaysCount)
                .addTo(CompositeDisposable())
    }

    private fun setJournalDates() {
        DbRepository.instance.journalDates()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { dates -> OnGetJournalDates(dates) }
                .addTo(CompositeDisposable())
    }

    //todo
    private fun firstViewAttachOnGetJournalDaysCount(countDays: Int) {
        if (countDays == 1) {
            viewState.setNextDayDisable()
        }
    }

    private fun OnGetJournalDates(dates: ArrayList<Long>) {
        viewState.setAdapterItems(dates, adapterPosition)
    }

    internal fun onFabClick() {
        App.getRouter().navigateTo(Const.Screen.ADD_DISH)
    }

    private fun moveToPosition(position: Int) {
        adapterPosition = position

        DbRepository.instance.getDateByIndex(position)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { date -> viewState.moveToPosition(position, formatDate(date)) }
                .addTo(CompositeDisposable())

        //viewState.moveToPosition(position, getDate(position))
    }

    /*private fun getDate(position: Int): String {

        return formatDate(DbRepository.instance.getDateByIndex(position))
    }*/

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
        setJournalDates()

        moveToPosition(adapterPosition)
        if (adapterPosition != 0) {
            viewState.setPrevDayDisable()
        } else {
            viewState.setPrevDayEnable()
        }

        DbRepository.instance.journalDaysCount()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::addDishEventOnGetJournalDaysCount)
                .addTo(CompositeDisposable())
    }

    //todo
    private fun addDishEventOnGetJournalDaysCount(countDays: Int) {
        if (adapterPosition + 1 == countDays) {
            viewState.setNextDayDisable()
        } else {
            viewState.setNextDayEnable()
        }
    }
}
