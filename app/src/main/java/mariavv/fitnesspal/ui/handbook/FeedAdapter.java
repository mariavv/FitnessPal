package mariavv.fitnesspal.ui.handbook;

import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import mariavv.fitnesspal.R;

class FeedAdapter extends RecyclerView.Adapter<ViewHolder> {

    private Cursor items;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.handbook_item, viewGroup, false);

        return new ViewHolder(v, items);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        items.moveToPosition(position);
        viewHolder.bindData();
    }

    @Override
    public int getItemCount() {
        if (items != null) {
            return items.getCount();
        }
        return 0;
    }

    public void updateItems(Cursor entities) {
        if (entities != null) {
            items = entities;
            notifyDataSetChanged();
        }
    }
}
