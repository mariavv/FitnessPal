package mariavv.fitnesspal.presentation

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

@StateStrategyType(SkipStrategy::class)
interface NavigateView : MvpView {
    fun setNavigator()

    fun removeNavigator()

    fun hideBottomMenu()

    fun showBottomMenu()

    fun showHomeAsUp(enabled: Boolean)
}
