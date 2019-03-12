package mariavv.fitnesspal.data.db.handbook

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import io.reactivex.Flowable

@Dao
interface HandbookDao {

    @Query("select * from foods order by sortable_name, name")
    fun getAll(): Flowable<List<Food>>

    @Insert
    fun insert(food: Food): Long

    /*@Query("SELECT * FROM employee WHERE id = :id")
    fun getById(id: Long): Food

    @Query("SELECT * FROM employee WHERE id = :name")
    fun getFoodId(name: String): Food

    @Delete
    fun delete(record: Food)*/
}
