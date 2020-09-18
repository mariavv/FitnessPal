package mariavv.fitnesspal.presentation.journal

import android.support.annotation.StringRes
import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface JournalView : MvpView {

    fun setDate(date: String)

    fun moveToPosition(position: Int, date: String)

    fun setAdapterItems(journalDates: ArrayList<Long>, adapterPosition: Int)

    fun setPrevDayEnable()

    fun setNextDayEnable()

    fun setPrevDayDisable()

    fun setNextDayDisable()

    fun setTitle(@StringRes title: Int)
}
