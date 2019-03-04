package mariavv.fitnesspal.presentation.common

import android.support.annotation.StringRes
import com.arellomobile.mvp.MvpAppCompatFragment

open class BaseFragment : MvpAppCompatFragment() {
    fun setTitle(@StringRes title: Int) {
        activity?.setTitle(title)
    }
}