package mariavv.fitnesspal.model.entity;

import java.util.Date;

public class Dish {
    private Date date;
    private int foodId;
    private int mass;

    public Dish(Date date, int foodId, int mass) {
        this.date = date;
        this.foodId = foodId;
        this.mass = mass;
    }

    public Date getDate() {
        return date;
    }

    public int getFoodId() {
        return foodId;
    }

    public int getMass() {
        return mass;
    }
}
