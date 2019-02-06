package mariavv.fitnesspal.model.repository;

import android.content.Context;
import android.database.Cursor;

import mariavv.fitnesspal.model.db.DbManager;
import mariavv.fitnesspal.model.entity.Food;

public class Repo {

    private static Repo instance;

    private DbManager db;

    private Repo(Context context) {
        db = DbManager.getInstance(context);
    }

    public static synchronized Repo getInstance(Context context) {
        if (instance == null) {
            instance = new Repo(context);
        }
        return instance;
    }

    public Cursor getFoodsFromHandbook() {
        return db.getFoodsFromHandbook();
    }

    public Cursor getJournal() {
        return db.getJournal();
    }

    public long insertFoodInHandbook(Food food) {
        return db.insertFoodInHandbook(food);
    }
}
