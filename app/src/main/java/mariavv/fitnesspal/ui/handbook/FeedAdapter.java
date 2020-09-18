package mariavv.fitnesspal.ui.handbook;

import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import mariavv.fitnesspal.R;
import mariavv.fitnesspal.model.db.DbManager;

class FeedAdapter extends RecyclerView.Adapter<ViewHolder> {

    private Cursor items;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        final View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.handbook_item, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        items.moveToPosition(position);

        setText(viewHolder.nameTv, DbManager.FOOD_NAME);
        setText(viewHolder.proteinTv, DbManager.FOOD_PROTEIN);
        setText(viewHolder.fatTv, DbManager.FOOD_FAT);
        setText(viewHolder.carbTv, DbManager.FOOD_CARB);
    }

    private void setText(TextView tv, String columnIndex) {
        tv.setText(items.getString(items.getColumnIndex(columnIndex)));
    }

    @Override
    public int getItemCount() {
        return items != null ? items.getCount() : 0;
    }

    void updateItems(Cursor c) {
        if (c != null) {
            items = c;
            notifyDataSetChanged();
        }
    }
}
