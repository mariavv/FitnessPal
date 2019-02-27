package mariavv.fitnesspal.domain;

import android.support.annotation.NonNull;

public class Food {

    private static final int PER_WEIGHT = 100;
    private static final int PROTEIN_ENERGY = 4;
    private static final int FAT_ENERGY = 9;
    private static final int CARB_ENERGY = 4;

    @NonNull
    private String name;
    private int protein;
    private int fat;
    private int carb;

    public Food(@NonNull String name, int protein, int fat, int carb) {
        this.name = name;
        this.protein = protein;
        this.fat = fat;
        this.carb = carb;
    }

    public int getEnergy(int weight) {
        return (protein * PROTEIN_ENERGY
                + fat * FAT_ENERGY
                + carb * CARB_ENERGY)
                * weight / PER_WEIGHT;
    }

    @NonNull
    public String getName() {
        return name;
    }

    public int getProtein() {
        return protein;
    }

    public int getFat() {
        return fat;
    }

    public int getCarb() {
        return carb;
    }

    public int getCount(int macronutrient, int weight) {
        return macronutrient * weight / PER_WEIGHT;
    }
}
