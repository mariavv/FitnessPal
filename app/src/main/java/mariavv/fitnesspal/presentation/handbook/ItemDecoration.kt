package mariavv.fitnesspal.presentation.handbook

import android.graphics.Rect
import android.support.v7.widget.RecyclerView
import android.view.View

internal class ItemDecoration(private val offset: Int) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, view: View,
                                parent: RecyclerView, state: RecyclerView.State) {

        if (parent.getChildAdapterPosition(view) == 0) {

            outRect.right = offset
            outRect.left = offset
            outRect.top = offset
            outRect.bottom = offset
        }
    }
}
