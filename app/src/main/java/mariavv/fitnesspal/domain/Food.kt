package mariavv.fitnesspal.domain

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import mariavv.fitnesspal.data.db.ColumnName
import mariavv.fitnesspal.data.db.TableName

@Entity(tableName = TableName.FOODS)
class Food(
        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = ColumnName.ID)
        var id: Int = 0,
        @ColumnInfo(name = ColumnName.NAME)
        var name: String,
        @ColumnInfo(name = ColumnName.PROTEIN)
        var protein: Int,
        @ColumnInfo(name = ColumnName.FAT)
        var fat: Int,
        @ColumnInfo(name = ColumnName.CARB)
        var carb: Int,
        @ColumnInfo(name = ColumnName.SORTABLE_NAME)
        var sortableName: String = "") {

    fun getEnergy(weight: Int): Int {
        return (protein * PROTEIN_ENERGY
                + fat * FAT_ENERGY
                + carb * CARB_ENERGY) * weight / PER_WEIGHT
    }

    companion object {
        private val PER_WEIGHT = 100
        private val PROTEIN_ENERGY = 4
        private val FAT_ENERGY = 9
        private val CARB_ENERGY = 4

        fun getMacronutrientInWeight(macronutrient: Int, weight: Int): Int {
            return macronutrient * weight / PER_WEIGHT
        }
    }

    constructor() : this(name = "", protein = 0, fat = 0, carb = 0)
}
