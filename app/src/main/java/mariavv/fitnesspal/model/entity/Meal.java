package mariavv.fitnesspal.model.entity;

import java.util.Date;

public class Meal {
    private Date date;
    private int food_id;
    private int mass;

    public Meal(Date date, int food_id, int mass) {
        this.date = date;
        this.food_id = food_id;
        this.mass = mass;
    }

    public Date getDate() {
        return date;
    }

    public int getFood_id() {
        return food_id;
    }

    public int getMass() {
        return mass;
    }
}
