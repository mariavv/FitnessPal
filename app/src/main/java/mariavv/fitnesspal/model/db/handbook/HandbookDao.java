package mariavv.fitnesspal.model.db.handbook;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface HandbookDao {

    @Query("SELECT * FROM employee")
    List<Food> getAll();

    @Query("SELECT * FROM employee WHERE id = :id")
    Food getById(long id);

    @Query("SELECT * FROM employee WHERE id = :name")
    Food getFoodId(String name);

    @Insert
    void insert(Food record);

    @Delete
    void delete(Food record);
}
