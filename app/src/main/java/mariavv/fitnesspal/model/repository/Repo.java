package mariavv.fitnesspal.model.repository;

import android.database.Cursor;

import java.util.Date;

import mariavv.fitnesspal.model.db.DbManager;
import mariavv.fitnesspal.model.model.Dish;
import mariavv.fitnesspal.model.model.Food;

public class Repo {

    private static Repo instance;

    private DbManager db;

    private Repo() {
        db = DbManager.getInstance();
    }

    public static synchronized Repo getInstance() {
        if (instance == null) {
            instance = new Repo();
        }
        return instance;
    }

    public Cursor getFoodsFromHandbook() {
        return db.getFoodsFromHandbook();
    }

    public Cursor getJournal() {
        return db.getJournal();
    }

    public void insertFoodInHandbook(Food food) {
        db.insertFoodInHandbook(food);
    }

    public int getJournalDaysCount() {
        return db.getJournalDaysCount().getInt(0);
    }

    public Date getDateByIndex(int i) {
        String date = db.getJournalDateByIndex(i).getString(0);
        return new Date();
    }

    public void insertDishInJournal(Dish dish) {
        db.insertDishInJournal(dish);
    }
}
