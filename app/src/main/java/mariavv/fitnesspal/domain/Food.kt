package mariavv.fitnesspal.domain

class Food(val name: String, val protein: Int, val fat: Int, val carb: Int) {

    fun getEnergy(weight: Int): Int {
        return (protein * PROTEIN_ENERGY
                + fat * FAT_ENERGY
                + carb * CARB_ENERGY) * weight / PER_WEIGHT
    }

    fun getCount(macronutrient: Int, weight: Int): Int {
        return macronutrient * weight / PER_WEIGHT
    }

    companion object {
        private val PER_WEIGHT = 100
        private val PROTEIN_ENERGY = 4
        private val FAT_ENERGY = 9
        private val CARB_ENERGY = 4
    }
}
