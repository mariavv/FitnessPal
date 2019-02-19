package mariavv.fitnesspal.ui.journal.daycard;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

class FeedAdapter extends RecyclerView.Adapter {
    private List<ItemType> dataSet = new ArrayList<>();

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        return ViewHolderFactory.create(viewGroup, viewType);
    }

    @Override
    public int getItemViewType(int position) {
        return dataSet.get(position).getItemViewType();
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        //todo??
        dataSet.get(position).onBindViewHolder(viewHolder);
    }

    @Override
    public int getItemCount() {
        if (dataSet != null) {
            return dataSet.size();
        }
        return 0;
    }

    void updateItems(List<ItemType> entities) {
        if (entities != null) {
            dataSet = entities;
            notifyDataSetChanged();
        }
    }
}
