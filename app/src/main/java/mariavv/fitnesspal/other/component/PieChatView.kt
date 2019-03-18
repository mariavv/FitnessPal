package mariavv.fitnesspal.other.component

import android.graphics.*
import android.graphics.Paint.ANTI_ALIAS_FLAG
import android.graphics.drawable.Drawable


class PieChatView : Drawable() {

    private val mPaint = Paint(ANTI_ALIAS_FLAG)
    private val mPath = Path()

    override fun draw(canvas: Canvas) {
        canvas.drawPath(mPath, mPaint);
    }

    override fun setAlpha(alpha: Int) {
        mPaint.setAlpha(alpha);
    }

    override fun getOpacity(): Int {
        return PixelFormat.TRANSLUCENT;
    }

    override fun setColorFilter(colorFilter: ColorFilter?) {
        mPaint.setColorFilter(colorFilter);
    }

    override fun onBoundsChange(bounds: Rect) {
        super.onBoundsChange(bounds)
        val width = bounds.width()
        val height = bounds.height()
        mPath.reset()
        mPath.moveTo(0f, height / 2f)
        mPath.lineTo(width / 4f, 0f)
        mPath.lineTo(width * 3 / 4f, 0f)
        mPath.lineTo(width * 1f, height / 2f)
        mPath.lineTo(width * 3 / 4f, height * 1f)
        mPath.lineTo(width / 4f, height * 1f)
        mPath.close()
    }
}