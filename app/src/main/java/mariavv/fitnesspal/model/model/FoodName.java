package mariavv.fitnesspal.model.model;

import android.support.annotation.NonNull;

public class FoodName {
    @NonNull
    public String value;

    public FoodName(@NonNull String foodName) {
        this.value = foodName;
    }
}
