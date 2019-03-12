package mariavv.fitnesspal.data.db.jounal

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "dishes")
class Dish(
        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = "id")
        var id: Int?,
        @ColumnInfo(name = "meal_num")
        var meal_num: Int?,
        @ColumnInfo(name = "date")
        var date: Long?,
        @ColumnInfo(name = "handbook_id")
        var handbook_id: Int?,
        @ColumnInfo(name = "mass")
        var mass: Int?
) /*{
    constructor() : this(0, 0, -1, 0, 0)
}*/
