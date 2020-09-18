package mariavv.fitnesspal.data.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import mariavv.fitnesspal.data.db.dao.DishDao
import mariavv.fitnesspal.data.db.dao.FoodsDao
import mariavv.fitnesspal.domain.Dish
import mariavv.fitnesspal.domain.Food


@Database(
        entities = [
            Food::class,
            Dish::class
        ],
        version = 1
)
abstract class Db : RoomDatabase() {
    abstract fun handbookDao(): FoodsDao
    abstract fun dishDao(): DishDao
}
