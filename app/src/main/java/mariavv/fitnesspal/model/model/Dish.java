package mariavv.fitnesspal.model.model;

import android.support.annotation.NonNull;

import java.util.Date;

public class Dish {
    @NonNull
    public Date date;
    public int foodId;
    public int mass;

    public Dish(@NonNull Date date, int foodId, int mass) {
        this.date = date;
        this.foodId = foodId;
        this.mass = mass;
    }
}
