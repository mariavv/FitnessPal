package mariavv.fitnesspal.model.model;

import android.support.annotation.NonNull;

public class Food {
    @NonNull
    public String name;
    public int protein;
    public int fat;
    public int carb;

    public Food(@NonNull String name, int protein, int fat, int carb) {
        this.name = name;
        this.protein = protein;
        this.fat = fat;
        this.carb = carb;
    }
}
