package mariavv.fitnesspal.presentation.handbook;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

class ItemDecoration extends RecyclerView.ItemDecoration {
    private int offset;

    public ItemDecoration(int offset) {
        this.offset = offset;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view,
                               RecyclerView parent, RecyclerView.State state) {

        if (parent.getChildAdapterPosition(view) == 0) {

            outRect.right = offset;
            outRect.left = offset;
            outRect.top = offset;
            outRect.bottom = offset;
        }
    }
}
