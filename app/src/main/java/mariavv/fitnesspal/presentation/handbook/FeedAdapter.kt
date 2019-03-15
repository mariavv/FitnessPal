package mariavv.fitnesspal.presentation.handbook

import android.database.Cursor
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import mariavv.fitnesspal.R
import mariavv.fitnesspal.data.db.ColumnName

internal class FeedAdapter : RecyclerView.Adapter<ViewHolder>() {

    private var items: Cursor? = null

    private var nameIndex: Int = 0
    private var proteinIndex: Int = 0
    private var fatIndex: Int = 0
    private var carbIndex: Int = 0

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
                .inflate(R.layout.handbook_item, viewGroup, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        items!!.moveToPosition(position)

        viewHolder.nameTv.text = items!!.getString(nameIndex)
        viewHolder.proteinTv.text = items!!.getString(proteinIndex)
        viewHolder.fatTv.text = items!!.getString(fatIndex)
        viewHolder.carbTv.text = items!!.getString(carbIndex)
    }

    override fun getItemCount(): Int {
        return if (items != null) items!!.count else 0
    }

    fun updateItems(c: Cursor?) {
        if (c != null) {
            if (items != null) items!!.close()

            items = c

            nameIndex = items!!.getColumnIndex(ColumnName.NAME)
            proteinIndex = items!!.getColumnIndex(ColumnName.PROTEIN)
            fatIndex = items!!.getColumnIndex(ColumnName.FAT)
            carbIndex = items!!.getColumnIndex(ColumnName.CARB)

            notifyDataSetChanged()
        }
    }
}
