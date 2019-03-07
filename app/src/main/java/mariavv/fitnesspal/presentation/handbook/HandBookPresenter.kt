package mariavv.fitnesspal.presentation.handbook

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import mariavv.fitnesspal.App
import mariavv.fitnesspal.R
import mariavv.fitnesspal.domain.interact.DbInteractor
import mariavv.fitnesspal.other.Const
import mariavv.fitnesspal.other.eventbus.AddFoodEvent
import mariavv.fitnesspal.other.eventbus.Status
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

@InjectViewState
class HandBookPresenter : MvpPresenter<HandBookView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.setTitle(R.string.handbook_title)
        viewState.updateFeed(DbInteractor.instance.foodsFromHandbook)
    }

    internal fun onFabClick() {
        App.getRouter().navigateTo(Const.Screen.ADD_FOOD)
    }

    fun onBackPressed() {
        App.getRouter().exit()
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onAddFoodEvent(event: AddFoodEvent) {
        if (event.status == Status.SUCCESS) {
            viewState.updateFeed(DbInteractor.instance.foodsFromHandbook)
        }
    }

    fun onCreate() {
        EventBus.getDefault().register(this)
    }

    override fun onDestroy() {
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this)
        }
    }
}
