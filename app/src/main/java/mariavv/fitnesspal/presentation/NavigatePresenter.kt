package mariavv.fitnesspal.presentation

import android.annotation.SuppressLint
import android.support.annotation.IdRes
import android.support.v4.app.FragmentManager
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import mariavv.fitnesspal.App
import mariavv.fitnesspal.data.repository.AssetsRepository
import mariavv.fitnesspal.data.repository.SharedDataRepository
import mariavv.fitnesspal.domain.data.Dish
import mariavv.fitnesspal.domain.data.TestData
import mariavv.fitnesspal.domain.interact.DbInteractor
import mariavv.fitnesspal.other.Const
import mariavv.fitnesspal.other.FrmFabric
import ru.terrakok.cicerone.Router
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

@InjectViewState
class NavigatePresenter : MvpPresenter<NavigateView>() {

    private lateinit var router: Router

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        router = App.getRouter()

        if (!SharedDataRepository.isNotFirstRun) {
            initDb()
            SharedDataRepository.saveNotFirstRun(true)
        } else {
            router.newRootScreen(Const.Screen.JOURNAL)
        }
    }

    internal fun onNavigationJournalSelected() {
        router.newRootScreen(Const.Screen.JOURNAL)
    }

    internal fun onNavigationHandbookSelected() {
        router.newRootScreen(Const.Screen.HANDBOOK)
    }

    internal fun onResume() {
        viewState.setNavigator()
    }

    internal fun onPause() {
        viewState.removeNavigator()
    }

    internal fun changeFragment(fragmentManager: FragmentManager, @IdRes containerId: Int) {
        val screenName = getFragmentName(fragmentManager, containerId)
        val needShowMenu = screenName == Const.Screen.HANDBOOK || screenName == Const.Screen.JOURNAL

        if (needShowMenu) {
            viewState.showBottomMenu()
            viewState.showHomeAsUp(false)
        } else {
            viewState.hideBottomMenu()
            viewState.showHomeAsUp(true)
        }
    }

    private fun getFragmentName(fragmentManager: FragmentManager, @IdRes containerId: Int): String {
        val fragment = fragmentManager.findFragmentById(containerId)
        return if (fragment is FrmFabric.IFragment) {
            (fragment as FrmFabric.IFragment).name
        } else ""
    }

    @SuppressLint("CheckResult")
    private fun initDb() {
        //test data
        //todo map
        Observable.fromCallable(AssetsRepository().getTestData(App.context.assets))
                .map { testData -> testData }.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { this.onGetTestData(it) }
    }

    private fun onGetTestData(data: TestData) {
        val repo = DbInteractor.instance

        data.foods?.forEach { food ->
            repo.addFood(food)
        }

        //todo dish2
        data.dishes?.forEach { dish ->
            //todo
            val format = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
            var docDate = Date()
            try {
                docDate = format.parse(dish.date)
            } catch (e: ParseException) {
                e.printStackTrace()
            }

            repo.insertDishInJournal(Dish(docDate, dish.meal, dish.foodId, dish.weight))
        }

        router.newRootScreen(Const.Screen.JOURNAL)
    }
}