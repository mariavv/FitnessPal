package mariavv.fitnesspal.presentation.handbook

import android.database.Cursor
import android.support.annotation.StringRes
import com.arellomobile.mvp.MvpView

//todo
//@StateStrategyType(AddToEndSingleStrategy::class)
interface HandBookView : MvpView {

    fun updateFeed(c: Cursor)

    fun setTitle(@StringRes title: Int)
}