package mariavv.fitnesspal.data.db.jounal;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.util.Date;

@Entity
public class Journal {
    @PrimaryKey
    public int id;
    public int meal_num;
    public Date date;
    public int handbook_id;
    public int mass;
}


