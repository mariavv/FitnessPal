package mariavv.fitnesspal.presentation.handbook

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView

import mariavv.fitnesspal.R

internal class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var nameTv: TextView
    var proteinTv: TextView
    var fatTv: TextView
    var carbTv: TextView

    init {

        nameTv = itemView.findViewById(R.id.hat)
        proteinTv = itemView.findViewById(R.id.protein_tv)
        fatTv = itemView.findViewById(R.id.fat_tv)
        carbTv = itemView.findViewById(R.id.carb_tv)
    }
}
