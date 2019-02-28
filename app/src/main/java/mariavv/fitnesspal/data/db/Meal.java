package mariavv.fitnesspal.data.db;

public enum Meal {
    BREAKFAST,
    LAUNCH,
    DINNER;

    public String getValue() {
        switch (this) {
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
