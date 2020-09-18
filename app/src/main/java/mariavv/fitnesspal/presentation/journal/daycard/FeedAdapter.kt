package mariavv.fitnesspal.presentation.journal.daycard

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import java.util.*

internal class FeedAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var dataSet: List<ItemType>? = ArrayList()

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolderFactory.create(viewGroup, viewType)
    }

    override fun getItemViewType(position: Int): Int {
        return dataSet!![position].itemViewType
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        //todo??
        dataSet!![position].onBindViewHolder(viewHolder)
    }

    override fun getItemCount(): Int {
        return if (dataSet != null) {
            dataSet!!.size
        } else 0
    }

    fun updateItems(dataSet: List<ItemType>) {
        this.dataSet = dataSet
        notifyDataSetChanged()
    }
}
