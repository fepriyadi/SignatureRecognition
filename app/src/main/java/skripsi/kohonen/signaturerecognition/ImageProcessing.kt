package skripsi.kohonen.signaturerecognition

import android.graphics.*

class ImageProcessing {
    private val w = 0
    private val h = 0

    companion object {
        fun getArrImage(bm: Bitmap): ByteArray {
            val arrImage = ByteArray(40000)
            var px: Int
            var r: Int
            var g: Int
            var b: Int
            var c = 0
            for (i in 0..199) {
                for (j in 0..199) {
                    px = bm.getPixel(j, i)
                    r = Color.red(px)
                    g = Color.green(px)
                    b = Color.blue(px)
                    px = (r + g + b) / 3
                    if (px < 127) arrImage[c] = 1 else arrImage[c] = 0
                    c++
                }
            }
            return arrImage
        }

        fun proses(bm: Bitmap): Bitmap {
            var bm = bm
            bm = crop(bm)
            bm = resize(bm, 200, 200)
            val bitmap = Bitmap.createBitmap(bm)
            bm.recycle()
            return bitmap
        }

        fun crop(bm: Bitmap): Bitmap {
            var px: Int
            var r: Int
            var g: Int
            var b: Int
            var x = bm.width
            var y = bm.height
            var w = 0
            var h = 0
            for (i in 0 until bm.height - 1) {
                for (j in 0 until bm.width - 1) {
                    px = bm.getPixel(j, i)
                    r = Color.red(px)
                    g = Color.green(px)
                    b = Color.blue(px)
                    px = (r + g + b) / 3
                    if (px == 0) {
                        if (j < x) x = j
                        if (i < y) y = i
                        if (j > w) w = j
                        if (i > h) h = i
                    }
                }
            }
            w -= x
            h -= y
            if (x == bm.width && y == bm.height && h == -y && w == -x) {
                x = 0
                y = 0
                w = bm.width
                h = bm.height
            }
            val croppedBitmap = Bitmap.createBitmap(bm, x, y, w, h)
            bm.recycle()
            return croppedBitmap
        }

        fun resize(bitmap: Bitmap, newWidth: Int, newHeight: Int): Bitmap {
            val scaledBitmap = Bitmap.createBitmap(newWidth, newHeight, Bitmap.Config.ARGB_8888)
            val ratioX = newWidth / bitmap.width.toFloat()
            val ratioY = newHeight / bitmap.height.toFloat()
            val middleX = newWidth / 2.0f
            val middleY = newHeight / 2.0f
            val scaleMatrix = Matrix()
            scaleMatrix.setScale(ratioX, ratioY, middleX, middleY)
            val canvas = Canvas(scaledBitmap)
            canvas.setMatrix(scaleMatrix)
            canvas.drawBitmap(bitmap, middleX - bitmap.width / 2, middleY - bitmap.height / 2, Paint(Paint.FILTER_BITMAP_FLAG))
            bitmap.recycle()
            return scaledBitmap
        }
    }
}