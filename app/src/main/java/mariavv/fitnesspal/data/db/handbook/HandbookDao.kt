package mariavv.fitnesspal.data.db.handbook

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import android.database.Cursor

@Dao
interface HandbookDao {

    @Query("select * from foods order by sortable_name, name")
    fun getAll(): Cursor

    @Query("select name, sortable_name from foods order by sortable_name, name")
    fun getFoodNames(): Cursor

    @Insert
    fun insert(food: Food): Long
}
