package mariavv.fitnesspal.model.model;

public class MealNum {

    private static final String breakfast = "Завтрак";
    private static final String lanch = "Обед";
    private static final String dinner = "Ужин";

    public int value;

    public MealNum(int mealNum) {
        this.value = mealNum;
    }

    public static String[] meals = {breakfast, lanch, dinner};

    public String getName() {
        return meals[value - 1];
    }
}
