package mariavv.fitnesspal.data.db.handbook

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query

@Dao
interface HandbookDao {

    @get:Query("SELECT * FROM employee")
    val all: List<Food>

    @Query("SELECT * FROM employee WHERE id = :id")
    fun getById(id: Long): Food

    @Query("SELECT * FROM employee WHERE id = :name")
    fun getFoodId(name: String): Food

    @Insert
    fun insert(record: Food)

    @Delete
    fun delete(record: Food)
}
