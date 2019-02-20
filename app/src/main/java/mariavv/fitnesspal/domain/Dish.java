package mariavv.fitnesspal.domain;

import android.support.annotation.NonNull;

import java.util.Date;

public class Dish {
    @NonNull
    public Date date;
    @NonNull
    public String meal;
    public int foodId;
    public int weight;

    public Dish(@NonNull Date date, @NonNull String meal, int foodId, int weight) {
        this.date = date;
        this.meal = meal;
        this.foodId = foodId;
        this.weight = weight;
    }
}
