package mariavv.fitnesspal.other;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import mariavv.fitnesspal.FitnessPal;
import mariavv.fitnesspal.R;

import static mariavv.fitnesspal.other.Const.DATE_PATTERN_DEFOULT;

public class Utils {

    public static String formatDate(String date) {
        String formattedDate = "";
        final Locale rus = new Locale(FitnessPal.getAppString(R.string.ru_locale));
        final DateFormat from = new SimpleDateFormat(DATE_PATTERN_DEFOULT, Locale.ENGLISH);
        try {
            formattedDate = DateFormat.getDateInstance(SimpleDateFormat.LONG, rus).format(from.parse(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return formattedDate;
    }

    public static Date getDate(int year, int month, int dayOfMonth) {
        final Calendar c = Calendar.getInstance();
        c.clear();
        c.set(year, month, dayOfMonth);
        return c.getTime();
    }
}
