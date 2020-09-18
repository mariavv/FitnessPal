package mariavv.fitnesspal.other

import mariavv.fitnesspal.App
import mariavv.fitnesspal.R
import mariavv.fitnesspal.other.Const.DATE_PATTERN_DEFOULT
import java.text.SimpleDateFormat
import java.util.*

object Utils {

    fun formatDate(timestamp: Long): String {
        //        String formattedDate = "";
        val rus = Locale(App.getAppString(R.string.ru_locale))
        val dateFormat = SimpleDateFormat(DATE_PATTERN_DEFOULT, rus)
        return dateFormat.format(Date(timestamp))
    }

    fun getDate(year: Int, month: Int, dayOfMonth: Int): Date {
        val c = Calendar.getInstance()
        c.clear()
        c.set(year, month, dayOfMonth)
        return c.time
    }
}
