package mariavv.fitnesspal.model.model;

import android.support.annotation.NonNull;

import java.util.Date;

public class Dish {
    public int mealNum;
    @NonNull
    public Date date;
    public int foodId;
    public int mass;

    public Dish(int mealNum, @NonNull Date date, int foodId, int mass) {
        this.mealNum = mealNum;
        this.date = date;
        this.foodId = foodId;
        this.mass = mass;
    }
}
