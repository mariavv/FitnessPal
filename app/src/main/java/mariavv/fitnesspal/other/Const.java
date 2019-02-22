package mariavv.fitnesspal.other;

import mariavv.fitnesspal.FitnessPal;

public class Const {

    public static final String LOG_TAG = FitnessPal.class.getSimpleName();

    public static final String DATE_PATTERN_DEFOULT = "EEE MMM dd HH:mm:ss z yyyy";

    public static class Screen {
        public static final String JOURNAL_SCREEN = "journal";
        public static final String HANDBOOK_SCREEN = "hand_book";
        public static final String ENTER_SCREEN = "enter";
        public static final String ADD_FOOD_SCREEN = "add_food";
    }

    public static class BundleArg {
        public static final String ARG_DATE = "date";
    }
}
