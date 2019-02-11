package mariavv.fitnesspal.model.model;

import android.support.annotation.NonNull;

import java.util.Date;

public class Dish {
    @NonNull
    public MealNum mealNum;
    @NonNull
    public Date date;
    public int foodId;
    @NonNull
    public Weight weight;

    public Dish(@NonNull MealNum mealNum, @NonNull Date date, int foodId, @NonNull Weight weight) {
        this.mealNum = mealNum;
        this.date = date;
        this.foodId = foodId;
        this.weight = weight;
    }
}
