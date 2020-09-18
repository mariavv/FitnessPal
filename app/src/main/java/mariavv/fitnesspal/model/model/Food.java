package mariavv.fitnesspal.model.model;

import android.support.annotation.NonNull;

public class Food {
    @NonNull
    public FoodName name;
    @NonNull
    public MacroNutrients macroNutrients;

    public Food(@NonNull FoodName name, @NonNull MacroNutrients macroNutrients) {
        this.name = name;
        this.macroNutrients = macroNutrients;
    }
}
