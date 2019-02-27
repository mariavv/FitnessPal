package mariavv.fitnesspal.other;

import mariavv.fitnesspal.FitnessPal;

public class Const {

    public static final String LOG_TAG = FitnessPal.class.getSimpleName();

    static final String DATE_PATTERN_DEFOULT = "dd MMMM yyyy";

    public static class Screen {
        public static final String JOURNAL = "journal";
        public static final String HANDBOOK = "hand_book";
        public static final String ADD_DISH = "add_dish";
        public static final String ADD_FOOD = "add_food";
    }

    public static class BundleArg {
        public static final String ARG_DATE = "date";
    }
}
