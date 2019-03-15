package mariavv.fitnesspal.data.db.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import android.database.Cursor
import io.reactivex.Flowable
import mariavv.fitnesspal.data.db.ColumnName
import mariavv.fitnesspal.data.db.TableName
import mariavv.fitnesspal.domain.Dish

@Dao
interface DishDao {

    @Query("select hb.${ColumnName.NAME}, j.${ColumnName.MEAL}, j.${ColumnName.DATE} , j.${ColumnName.WEIGHT} , hb.${ColumnName.PROTEIN}, hb.${ColumnName.FAT} , hb.${ColumnName.CARB} from ${TableName.FOODS} as hb, ${TableName.DISHES} as j where j.${ColumnName.DATE} = :date and j.${ColumnName.FOOD_ID} = hb.${ColumnName.ID} order by j.${ColumnName.MEAL}")
    fun getJournal(date: Long): Cursor

    @Query("select count(${ColumnName.DATE}) as count from ( select distinct ${ColumnName.DATE} from ${TableName.DISHES})")
    fun getJournalDaysCount(): Flowable<Int>

    @Query("select distinct ${ColumnName.DATE} from ${TableName.DISHES} order by ${ColumnName.DATE}")
    fun getJournalDates(): Cursor

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(dish: Dish): Long
}