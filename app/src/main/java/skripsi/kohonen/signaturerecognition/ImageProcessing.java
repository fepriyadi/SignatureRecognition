package skripsi.kohonen.signaturerecognition;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.Log;


public class ImageProcessing {

    private int w;
    private int h;
    public static byte[] getArrImage(Bitmap bm) {
        byte[] arrImage = new byte[40000];
        int px, r, g, b;
        int c = 0;
        for (int i=0; i<200; i++) {
            for (int j=0; j<200; j++) {
                px = bm.getPixel(j,i);
                r = Color.red(px);
                g = Color.green(px);
                b = Color.blue(px);
                px = (r+g+b)/3;
                if (px < 127) arrImage[c] = 1;
                else
                    arrImage[c] = 0;
                c++;
            }
        }
        return arrImage;

    }

    public static Bitmap proses(Bitmap bm) {
        bm = crop(bm);
        bm = resize(bm, 200, 200);
        Bitmap bitmap = Bitmap.createBitmap(bm);
        bm.recycle();
        return bitmap;
    }

    public static Bitmap crop(Bitmap bm) {
        int px, r, g, b;
        int x = bm.getWidth();
        int y = bm.getHeight();
        int w = 0;
        int h = 0;
        for (int i=0; i<bm.getHeight()-1; i++) {
            for (int j=0; j<bm.getWidth()-1; j++) {
                px = bm.getPixel(j,i);
                r = Color.red(px);
                g = Color.green(px);
                b = Color.blue(px);
                px = (r+g+b)/3;
                if (px == 0) {
                    if (j < x) x = j;
                    if (i < y) y = i;
                    if (j > w) w = j;
                    if (i > h) h = i;
                }
            }
        }
        w -= x;
        h -= y;

        if(x==bm.getWidth() && y==bm.getHeight() && h==-y && w==-x) {
            x=0;
            y=0;
            w = bm.getWidth();
            h = bm.getHeight();
        }

        Bitmap croppedBitmap = Bitmap.createBitmap(bm, x, y, w, h);
        bm.recycle();
        return croppedBitmap;
    }

    public static Bitmap resize(Bitmap bitmap,int newWidth,int newHeight) {
        Bitmap scaledBitmap = Bitmap.createBitmap(newWidth, newHeight, Bitmap.Config.ARGB_8888);

        float ratioX = newWidth / (float) bitmap.getWidth();
        float ratioY = newHeight / (float) bitmap.getHeight();
        float middleX = newWidth / 2.0f;
        float middleY = newHeight / 2.0f;

        Matrix scaleMatrix = new Matrix();
        scaleMatrix.setScale(ratioX, ratioY, middleX, middleY);

        Canvas canvas = new Canvas(scaledBitmap);
        canvas.setMatrix(scaleMatrix);
        canvas.drawBitmap(bitmap, middleX - bitmap.getWidth() / 2, middleY - bitmap.getHeight() / 2, new Paint(Paint.FILTER_BITMAP_FLAG));
        bitmap.recycle();
        return scaledBitmap;
    }
}
