package mariavv.fitnesspal.model.db.handbook;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class Food {

    @PrimaryKey
    public int id;
    public String name;
    public int protein;
    public int fat;
    public int carb;
}
