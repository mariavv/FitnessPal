package mariavv.fitnesspal.data.db;

public enum Meal {
    BREAKFAST,
    LAUNCH,
    DINNER;

    public static String getValue(Meal meal) {
        switch (meal) {
            case BREAKFAST:
                return "Завтрак";
            case LAUNCH:
                return "Обед";
            case DINNER:
                return "Ужин";
        }
        return "";
    }
}
