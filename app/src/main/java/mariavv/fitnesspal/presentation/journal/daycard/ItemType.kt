package mariavv.fitnesspal.presentation.journal.daycard

import android.support.v7.widget.RecyclerView

interface ItemType {

    val itemViewType: Int

    fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder)

    companion object {
        val HEADER = 0
        val MEAL = 1
        val DISH = 2
    }
}
