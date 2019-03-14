package mariavv.fitnesspal.data.db.jounal

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "dishes")
class Dish(
        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = "id")
        var id: Int = 0,
        @ColumnInfo(name = "meal_num")
        var mealNum: String,
        @ColumnInfo(name = "date")
        var date: Long,
        @ColumnInfo(name = "handbook_id")
        var handbookId: Int,
        @ColumnInfo(name = "mass")
        var mass: Int
)
