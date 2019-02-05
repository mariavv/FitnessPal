package mariavv.fitnesspal.model.entity;

public class Food {
    private String name;
    private String protein;
    private String fat;
    private String carb;

    public Food(String name, String protein, String fat, String carb) {
        this.name = name;
        this.protein = protein;
        this.fat = fat;
        this.carb = carb;
    }

    public String getName() {
        return name;
    }

    public String getProtein() {
        return protein;
    }

    public String getFat() {
        return fat;
    }

    public String getCarb() {
        return carb;
    }
}
