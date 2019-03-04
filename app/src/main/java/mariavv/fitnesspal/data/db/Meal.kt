package mariavv.fitnesspal.data.db

enum class Meal {
    BREAKFAST,
    LAUNCH,
    DINNER;

    val value: String
        get() {
            return when (this) {
                BREAKFAST -> "Завтрак"
                LAUNCH -> "Обед"
                DINNER -> "Ужин"
            }
        }
}
