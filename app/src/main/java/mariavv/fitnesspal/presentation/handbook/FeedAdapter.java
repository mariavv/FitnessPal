package mariavv.fitnesspal.presentation.handbook;

import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import mariavv.fitnesspal.R;
import mariavv.fitnesspal.data.db.CName;

class FeedAdapter extends RecyclerView.Adapter<ViewHolder> {

    private Cursor items;
    private int nameIndex;
    private int proteinIndex;
    private int fatIndex;
    private int carbIndex;

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

        setText(viewHolder.nameTv, nameIndex);
        setText(viewHolder.proteinTv, proteinIndex);
        setText(viewHolder.fatTv, fatIndex);
        setText(viewHolder.carbTv, carbIndex);

        //todo
        //viewHolder.nameTv.setText(items.getString(index));
    }

    private void setText(TextView tv, int index) {
        tv.setText(items.getString(index));
    }

    @Override
    public int getItemCount() {
        return items != null ? items.getCount() : 0;
    }

    void updateItems(Cursor c) {
        if (c != null) {
            items = c;
            nameIndex = items.getColumnIndex(CName.NAME);
            proteinIndex = items.getColumnIndex(CName.PROTEIN);
            fatIndex = items.getColumnIndex(CName.FAT);
            carbIndex = items.getColumnIndex(CName.CARB);
            notifyDataSetChanged();
        }
    }
}
