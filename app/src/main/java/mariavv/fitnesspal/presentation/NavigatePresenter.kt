package mariavv.fitnesspal.presentation

import android.annotation.SuppressLint
import android.arch.persistence.room.Room
import android.support.annotation.IdRes
import android.support.v4.app.FragmentManager
import android.util.Log
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import mariavv.fitnesspal.App
import mariavv.fitnesspal.data.db.AppDatabase
import mariavv.fitnesspal.data.repository.AssetsRepository
import mariavv.fitnesspal.data.repository.DbRepository
import mariavv.fitnesspal.data.repository.SharedDataRepository
import mariavv.fitnesspal.domain.Dish
import mariavv.fitnesspal.domain.StartedData
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

        var db: AppDatabase = Room.databaseBuilder(App.context,
                AppDatabase::class.java, "fit").fallbackToDestructiveMigration().build()
        val handbookDao = db.handbookDao()

        /*val x = Single.fromCallable(Callable {
            handbookDao.insert(mariavv.fitnesspal.data.db.handbook.Food(1,"f", 1, 1,1,  "f"))
        }).blockingGet()*/

        //val x = Flowable.fromCallable { handbookDao.insert(mariavv.fitnesspal.data.db.handbook.Food(1,"f", 1, 1,1,  "f")) }
        //        .blockingFirst()

        //val x = Completable
        //        .fromRunnable { handbookDao.insert(mariavv.fitnesspal.data.db.handbook.Food(1,"f", 1, 1,1,  "f")) }
        //.toFlowable<Long>().blockingFirst()

        Single.fromCallable {
            handbookDao.insert(mariavv.fitnesspal.data.db.handbook.Food(1, "f", 1, 1, 1, "f")
            )
        }.subscribeOn(
                Schedulers.io()
        ).subscribeBy(
                onError = { t ->
                    Log.d("!!!", "insert error ${t.message}")
                },
                onSuccess = { id ->
                    Log.d("!!!", "insert success, id = $id")
                }

        ).addTo(CompositeDisposable())
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

    private fun onGetTestData(data: StartedData) {
        data.foods?.forEach { food ->
            DbRepository.instance.insertFoodInHandbook(food)
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

            DbRepository.instance.insertDishInJournal(Dish(docDate, dish.meal, dish.foodId, dish.weight))
        }

        router.newRootScreen(Const.Screen.JOURNAL)
    }
}
