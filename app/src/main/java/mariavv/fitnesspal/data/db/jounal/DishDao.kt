package mariavv.fitnesspal.data.db.jounal

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import android.database.Cursor
import io.reactivex.Flowable

@Dao
interface DishDao {

    @Query("select hb.name, j.meal, j.date , j.weight , hb.protein , hb.fat , hb.carb from foods as hb, dishes as j where j.date = :date and j.food_id = hb.id order by j.meal")
    fun getJournal(date: Long): Cursor

    @Query("select count(date) as count from ( select distinct date from dishes)")
    fun getJournalDaysCount(): Flowable<Int>

    @Query("select distinct date from dishes order by date")
    fun getJournalDates(): Cursor

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(dish: Dish): Long
}