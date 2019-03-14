package mariavv.fitnesspal.data.db.handbook

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import android.database.Cursor
import io.reactivex.Flowable

@Dao
interface FoodsDao {

    @Query("select * from foods order by sortable_name, name")
    fun getAll(): Cursor

    @Query("select name, sortable_name from foods order by sortable_name, name")
    fun getFoodNames(): Cursor

    @Query("select id from foods where name = :name")
    fun getFoodIdByName(name: String): Flowable<Int>

    @Insert(onConflict = OnConflictStrategy.FAIL)
    fun insert(food: Food): Long
}
