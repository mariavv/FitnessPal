package mariavv.fitnesspal.data.db.jounal

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import java.util.*

@Entity
class Journal {
    @PrimaryKey
    var id: Int = 0
    var meal_num: Int = 0
    var date: Date? = null
    var handbook_id: Int = 0
    var mass: Int = 0
}


