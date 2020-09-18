package mariavv.fitnesspal.presentation.handbook

import android.database.Cursor
import android.support.annotation.StringRes
import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface HandBookView : MvpView {

    fun updateFeed(c: Cursor)

    fun setTitle(@StringRes title: Int)
}