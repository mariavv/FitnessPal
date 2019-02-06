package mariavv.fitnesspal.model.entity;

public class Food {
    private String name;
    private int protein;
    private int fat;
    private int carb;

    public Food(String name, int protein, int fat, int carb) {
        this.name = name;
        this.protein = protein;
        this.fat = fat;
        this.carb = carb;
    }

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
}
