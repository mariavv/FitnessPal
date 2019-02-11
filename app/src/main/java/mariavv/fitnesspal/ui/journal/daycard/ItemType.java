package mariavv.fitnesspal.ui.journal.daycard;

import android.support.v7.widget.RecyclerView;

public interface ItemType {
    int HEADER = 0;
    int MEAL = 1;
    int DISH = 2;

    int getItemViewType();

    void onBindViewHolder(RecyclerView.ViewHolder viewHolder);
}
