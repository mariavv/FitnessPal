package mariavv.fitnesspal.presentation.handbook

import android.database.Cursor
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
import mariavv.fitnesspal.other.eventbus.AddFoodEvent
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

@InjectViewState
class HandBookPresenter : MvpPresenter<HandBookView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.setTitle(R.string.handbook_title)
        getFeed()
    }

    private fun getFeed() {
        DbRepository.instance.foodsFromHandbook
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onGetFeed)
                .addTo(CompositeDisposable())
    }

    private fun onGetFeed(foods: Cursor) {
        viewState.updateFeed(foods)
    }

    internal fun onFabClick() {
        App.getRouter().navigateTo(Const.Screen.ADD_FOOD)
    }

    internal fun onBackPressed() {
        App.getRouter().exit()
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onAddFoodEvent(event: AddFoodEvent) {
        getFeed()
    }

    internal fun onStart() {
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this)
        }
    }

    override fun onDestroy() {
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this)
        }
    }
}
