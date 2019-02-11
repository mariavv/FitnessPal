package mariavv.fitnesspal.ui.journal.daycard;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import mariavv.fitnesspal.R;

public class ViewHolderFactory {

    @NonNull
    static RecyclerView.ViewHolder create(ViewGroup parent, int viewType) {
        switch (viewType) {
            case ItemType.HEADER:
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.day_header_item, parent, false);
                return new ViewHolderFactory.HeaderViewHolder(view);

            case ItemType.MEAL:
                return listItemViewHolder(parent);

            case ItemType.DISH:
                return listItemViewHolder(parent);

            default:
                return null;
        }
    }

    private static ListItemViewHolder listItemViewHolder(ViewGroup parent) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.day_meal_list_item, parent, false);
        return new ViewHolderFactory.ListItemViewHolder(v);
    }

    public static class HeaderViewHolder extends RecyclerView.ViewHolder {

        public TextView proteinTv;
        public TextView fatTv;
        public TextView carbTv;
        public TextView energyTv;

        HeaderViewHolder(View itemView) {
            super(itemView);

            proteinTv = itemView.findViewById(R.id.protein_tv);
            fatTv = itemView.findViewById(R.id.fat_tv);
            carbTv = itemView.findViewById(R.id.carb_tv);
            energyTv = itemView.findViewById(R.id.energy_tv);
        }
    }

    public static class ListItemViewHolder extends RecyclerView.ViewHolder {

        public TextView mealTitle;
        public TextView weight;
        public TextView proteinTv;
        public TextView fatTv;
        public TextView carbTv;
        public TextView energyTv;

        ListItemViewHolder(View itemView) {
            super(itemView);

            mealTitle = itemView.findViewById(R.id.meal_title_tv);
            weight = itemView.findViewById(R.id.weight_tv);
            proteinTv = itemView.findViewById(R.id.protein_tv);
            fatTv = itemView.findViewById(R.id.fat_tv);
            carbTv = itemView.findViewById(R.id.carb_tv);
            energyTv = itemView.findViewById(R.id.energy_tv);
        }
    }
}
