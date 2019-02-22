package mariavv.fitnesspal.other;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import mariavv.fitnesspal.FitnessPal;
import mariavv.fitnesspal.R;

import static mariavv.fitnesspal.other.Const.DATE_PATTERN_DEFOULT;

public class Utils {

    public static String formatDate(long timestamp) {
//        String formattedDate = "";
        final Locale rus = new Locale(FitnessPal.getAppString(R.string.ru_locale));
        final SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_PATTERN_DEFOULT, rus);
        return dateFormat.format(new Date(timestamp));
    }

    public static Date getDate(int year, int month, int dayOfMonth) {
        final Calendar c = Calendar.getInstance();
        c.clear();
        c.set(year, month, dayOfMonth);
        return c.getTime();
    }
}
