package skripsi.kohonen.signaturerecognition

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View

/**
 * Created by fep on 29/03/2017.
 */
class FiturDrawing(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
    private var drawPath: Path? = null
    private var drawPaint: Paint? = null
    private var canvasPaint: Paint? = null
    private val paintColor = -0x1000000
    var drawCanvas: Canvas? = null
    private var canvasBitmap: Bitmap? = null
    fun clear() {
        drawCanvas!!.drawColor(0, PorterDuff.Mode.CLEAR)
        invalidate()
    }

    private fun setupDrawing() {
        drawPath = Path()
        drawPaint = Paint()
        drawPaint!!.color = paintColor
        drawPaint!!.isAntiAlias = true
        drawPaint!!.strokeWidth = 20f
        drawPaint!!.style = Paint.Style.STROKE
        drawPaint!!.strokeJoin = Paint.Join.ROUND
        drawPaint!!.strokeCap = Paint.Cap.ROUND
        canvasPaint = Paint(Paint.DITHER_FLAG)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        canvasBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888)
        drawCanvas = Canvas(canvasBitmap)
    }

    override fun onDraw(canvas: Canvas) {
        canvas.drawBitmap(canvasBitmap!!, 0f, 0f, canvasPaint)
        canvas.drawPath(drawPath!!, drawPaint!!)
    }

    fun getDrawPath(): Path? {
        return drawPath
    }

    fun getDrawPaint(): Paint? {
        return drawPaint
    }

    fun getDrawCanvas(): Canvas? {
        return drawCanvas
    }

    init {
        setupDrawing()
    }
}