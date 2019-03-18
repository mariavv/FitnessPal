package mariavv.fitnesspal.data.db.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import android.database.Cursor
import mariavv.fitnesspal.data.db.ColumnName
import mariavv.fitnesspal.data.db.TableName
import mariavv.fitnesspal.domain.Food

@Dao
interface FoodsDao {

    @Query("select * from ${TableName.FOODS} order by ${ColumnName.SORTABLE_NAME}, ${ColumnName.NAME}")
    fun getAll(): Cursor

    @Query("select ${ColumnName.NAME}, ${ColumnName.SORTABLE_NAME} from ${TableName.FOODS} order by ${ColumnName.SORTABLE_NAME}, ${ColumnName.NAME}")
    fun getFoodNames(): Cursor

    @Query("select ${ColumnName.ID} from ${TableName.FOODS} where ${ColumnName.NAME} = :name")
    fun getFoodIdByName(name: String): Int

    @Insert(onConflict = OnConflictStrategy.FAIL)
    fun insert(food: Food): Long
}
