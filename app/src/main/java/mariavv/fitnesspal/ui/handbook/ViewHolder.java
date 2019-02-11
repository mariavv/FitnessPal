package mariavv.fitnesspal.ui.handbook;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import mariavv.fitnesspal.R;

class ViewHolder extends RecyclerView.ViewHolder {

    TextView nameTv;
    TextView proteinTv;
    TextView fatTv;
    TextView carbTv;

    ViewHolder(@NonNull View itemView) {
        super(itemView);

        nameTv = itemView.findViewById(R.id.name_tv);
        proteinTv = itemView.findViewById(R.id.protein_tv);
        fatTv = itemView.findViewById(R.id.fat_tv);
        carbTv = itemView.findViewById(R.id.carb_tv);
    }
}
