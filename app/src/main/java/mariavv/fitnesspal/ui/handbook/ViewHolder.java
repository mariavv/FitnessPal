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

        nameTv = itemView.findViewById(R.id.nameTv);
        proteinTv = itemView.findViewById(R.id.proteinTv);
        fatTv = itemView.findViewById(R.id.fatTv);
        carbTv = itemView.findViewById(R.id.carbTv);
    }
}
