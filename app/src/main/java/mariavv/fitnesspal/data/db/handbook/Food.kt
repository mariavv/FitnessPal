package mariavv.fitnesspal.data.db.handbook

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "foods")
class Food(
        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = "id")
        var id: Int?,
        @ColumnInfo(name = "name")
        var name: String?,
        @ColumnInfo(name = "protein")
        var protein: Int?,
        @ColumnInfo(name = "fat")
        var fat: Int?,
        @ColumnInfo(name = "carb")
        var carb: Int?,
        @ColumnInfo(name = "sortable_name")
        var sortable_name: String?
) /*{
        constructor() : this(0, "", 0, 0, 0, "")
    }*/
