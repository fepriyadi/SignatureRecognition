package skripsi.kohonen.signaturerecognition;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by fep on 29/03/2017.
 */

public class FiturDrawing extends View
{
  private Path drawPath;
  private Paint drawPaint, canvasPaint;
  private int paintColor = 0xFF000000;
  
  
  public Canvas drawCanvas;
  private Bitmap canvasBitmap;
  
  public FiturDrawing(Context context, AttributeSet attrs)
  {
    super(context, attrs);
    setupDrawing();
  }
  
  public void clear()
  {
    drawCanvas.drawColor(0, PorterDuff.Mode.CLEAR);
    invalidate();
  }
  
  private void setupDrawing()
  {
    drawPath = new Path();
    drawPaint = new Paint();
    drawPaint.setColor(paintColor);
    drawPaint.setAntiAlias(true);
    drawPaint.setStrokeWidth(20);
    drawPaint.setStyle(Paint.Style.STROKE);
    drawPaint.setStrokeJoin(Paint.Join.ROUND);
    drawPaint.setStrokeCap(Paint.Cap.ROUND);
    canvasPaint = new Paint(Paint.DITHER_FLAG);
  }
  
  @Override
  protected void onSizeChanged(int w, int h, int oldw, int oldh)
  {
    super.onSizeChanged(w, h, oldw, oldh);
    canvasBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
    drawCanvas = new Canvas(canvasBitmap);
  }
  
  @Override
  protected void onDraw(Canvas canvas)
  {
    canvas.drawBitmap(canvasBitmap, 0, 0, canvasPaint);
    canvas.drawPath(drawPath, drawPaint);
  }
  
  public Path getDrawPath()
  {
    return drawPath;
  }
  
  public Paint getDrawPaint()
  {
    return drawPaint;
  }
  
  public Canvas getDrawCanvas()
  {
    return drawCanvas;
  }
  
}
