package mariavv.fitnesspal.presentation.handbook

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import mariavv.fitnesspal.App
import mariavv.fitnesspal.R
import mariavv.fitnesspal.domain.interact.DbInteractor
import mariavv.fitnesspal.other.Const

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
}
