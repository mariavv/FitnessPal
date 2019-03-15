package mariavv.fitnesspal.domain

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import mariavv.fitnesspal.data.db.ColumnName
import mariavv.fitnesspal.data.db.TableName

@Entity(tableName = TableName.DISHES)
class Dish(
        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = ColumnName.ID)
        var id: Int = 0,
        @ColumnInfo(name = ColumnName.MEAL)
        var meal: String,
        @ColumnInfo(name = ColumnName.DATE)
        var date: Long,
        @ColumnInfo(name = ColumnName.FOOD_ID)
        var foodId: Int,
        @ColumnInfo(name = ColumnName.WEIGHT)
        var weight: Int) {

    constructor() : this(meal = "", date = 0, foodId = 0, weight = 0)
}
