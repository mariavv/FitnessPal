package mariavv.fitnesspal.data.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import mariavv.fitnesspal.data.db.handbook.Food
import mariavv.fitnesspal.data.db.handbook.HandbookDao
import mariavv.fitnesspal.data.db.jounal.DishDao


@Database(entities = [Food::class, mariavv.fitnesspal.data.db.jounal.Dish::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun handbookDao(): HandbookDao
    abstract fun dishDao(): DishDao
}
