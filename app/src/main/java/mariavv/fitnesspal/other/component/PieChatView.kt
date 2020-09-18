package mariavv.fitnesspal.other.component

import android.graphics.*
import android.graphics.Paint.ANTI_ALIAS_FLAG
import android.graphics.drawable.Drawable


class PieChatView : Drawable() {

    private val paint = Paint(ANTI_ALIAS_FLAG)
    private val path = Path()

    override fun draw(canvas: Canvas) {
        canvas.drawPath(path, paint);

        //paint.color = Color.RED
        //canvas.drawOval(0f, 0f, 380 * 1f, 380 * 1f, paint);

        val rectf = RectF(0f, 0f, 390f, 390f)

        paint.setColor(Color.GREEN);
        paint.setStrokeWidth(10f);

        // перенастраиваем кисть на зеленый цвет
        paint.setColor(Color.RED);

        // рисуем овал внутри прямоугольника rectf
        canvas.drawOval(rectf, paint);

        // смещаем rectf в (900,100) (левая верхняя точка)
        rectf.offsetTo(900f, 100f);
        // увеличиваем rectf по вертикали на 25 вниз и вверх
        rectf.inset(0f, -25f);
        // рисуем дугу внутри прямоугольника rectf
        // с началом в 90, и длиной 270
        // соединение крайних точек через центр
        canvas.drawArc(rectf, 90f, 270f, true, paint);
    }

    override fun setAlpha(alpha: Int) {
        paint.setAlpha(alpha);
    }

    override fun getOpacity(): Int {
        return PixelFormat.TRANSLUCENT;
    }

    override fun setColorFilter(colorFilter: ColorFilter?) {
        paint.setColorFilter(colorFilter);
    }

    override fun onBoundsChange(bounds: Rect) {
        super.onBoundsChange(bounds)
        val width = bounds.width()
        val height = bounds.height()
        /*path.reset()
        path.moveTo(0f, height / 2f)
        path.lineTo(width / 4f, 0f)
        path.lineTo(width * 3 / 4f, 0f)
        path.lineTo(width * 1f, height / 2f)
        path.lineTo(width * 3 / 4f, height * 1f)
        path.lineTo(width / 4f, height * 1f)
        path.close()*/
    }
}