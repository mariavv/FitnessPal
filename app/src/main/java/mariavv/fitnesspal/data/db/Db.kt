package mariavv.fitnesspal.data.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import mariavv.fitnesspal.data.db.handbook.FoodsDao
import mariavv.fitnesspal.data.db.jounal.DishDao


@Database(
        entities = [
            mariavv.fitnesspal.data.db.handbook.Food::class,
            mariavv.fitnesspal.data.db.jounal.Dish::class
        ],
        version = 1
)
abstract class Db : RoomDatabase() {
    abstract fun handbookDao(): FoodsDao
    abstract fun dishDao(): DishDao
}
