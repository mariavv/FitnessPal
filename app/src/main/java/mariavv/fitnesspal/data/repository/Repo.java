package mariavv.fitnesspal.data.repository;

import android.database.Cursor;

import java.util.Date;

import mariavv.fitnesspal.domain.Dish;
import mariavv.fitnesspal.domain.Food;

public class Repo {

    private static Repo instance;

    private DbManager db;

    private Repo() {
        db = new DbManager();
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

    public Cursor getJournal(String date) {
        return db.getJournal(date);
    }

    public void insertFoodInHandbook(Food food) {
        db.insertFoodInHandbook(food);
    }

    public int getJournalDaysCount() {
        final Cursor c = db.getJournalDaysCount();
        c.moveToFirst();
        final int count = c.getInt(0);
        c.close();
        return count;
    }

    public String getDateByIndex(int i) {
        final Cursor c = db.getJournalDates();
        c.moveToPosition(i);
        String date = c.getString(0);
        c.close();
        return date;
    }

    public void insertDishInJournal(Dish dish) {
        db.insertDishInJournal(dish);
    }

    public void clearJournal() {
        db.clearJournal();
    }

    public Cursor getFoofList() {
        return db.getFoodNamesFromHandbook();
    }

    public void addDish(Date date, String meal, String dish, int weight) {
        try {
            db.insertDishInJournal(new Dish(date, meal, db.getFoodIdByName(dish), weight));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addFood(String food, String protein, String fat, String carb) {
        db.insertFoodInHandbook(new Food(food, Integer.valueOf(protein), Integer.valueOf(fat), Integer.valueOf(carb)));
    }
}
