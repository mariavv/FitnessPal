package mariavv.fitnesspal.data.db.handbook

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
class Food {

    @PrimaryKey
    var id: Int = 0
    var name: String? = null
    var protein: Int = 0
    var fat: Int = 0
    var carb: Int = 0
}
