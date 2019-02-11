package mariavv.fitnesspal.model.model;

public class Energy {
    private static final int proteinEnergy = 4;
    private static final int fatEnergy = 9;
    private static final int carbEnergy = 4;
    private static final int PER_WEIGHT = 100;

    private int value;

    public Energy(MacroNutrients macroNutrients, Weight weight) {
        this.value = (macroNutrients.protein * proteinEnergy
                + macroNutrients.fat * fatEnergy
                + macroNutrients.carb * carbEnergy)
                * weight.value / PER_WEIGHT;
    }

    public int getEnergy() {
        return value;
    }
}
