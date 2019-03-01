package mariavv.fitnesspal.domain;

import com.google.gson.annotations.SerializedName;

public class Dish2 {

    public String date;
    public String meal;
    @SerializedName("food_id")
    public int foodId;
    public int weight;

    public Dish2(String date, String meal, int foodId, int weight) {
        this.date = date;
        this.meal = meal;
        this.foodId = foodId;
        this.weight = weight;
    }
}
