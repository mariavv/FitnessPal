package mariavv.fitnesspal.ui.handbook;

import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import mariavv.fitnesspal.R;
import mariavv.fitnesspal.model.db.DbManager;

class ViewHolder extends RecyclerView.ViewHolder {

    private TextView nameTv;
    private TextView proteinTv;
    private TextView fatTv;
    private TextView carbTv;

    private Cursor items;

    ViewHolder(@NonNull View itemView, Cursor items) {
        super(itemView);

        this.items = items;

        nameTv = itemView.findViewById(R.id.nameTv);
        proteinTv = itemView.findViewById(R.id.proteinTv);
        fatTv = itemView.findViewById(R.id.fatTv);
        carbTv = itemView.findViewById(R.id.carbTv);
    }

    void bindData() {
        nameTv.setText(items.getString(items.getColumnIndex(DbManager.FOOD_NAME)));
        proteinTv.setText(items.getString(items.getColumnIndex(DbManager.FOOD_PROTEIN)));
        fatTv.setText(items.getString(items.getColumnIndex(DbManager.FOOD_FAT)));
        carbTv.setText(items.getString(items.getColumnIndex(DbManager.FOOD_CARB)));
    }
}
