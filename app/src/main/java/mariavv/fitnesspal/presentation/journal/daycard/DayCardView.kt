package mariavv.fitnesspal.presentation.journal.daycard

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.SingleStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

@StateStrategyType(SingleStateStrategy::class)
interface DayCardView : MvpView {
    fun updateCard(dataSet: List<ItemType>, dayProtein: Int, dayFat: Int, dayCarb: Int, dayEnergy: Int)
}
