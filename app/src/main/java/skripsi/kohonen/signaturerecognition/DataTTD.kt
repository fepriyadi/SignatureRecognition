package skripsi.kohonen.signaturerecognition

import android.graphics.Color
import android.widget.ImageView

class DataTTD : AppCompatActivity() {
    @InjectView(R.id.iv_full)
    var ivFull: ImageView? = null

    @InjectView(R.id.tv_array)
    var tvArray: TextView? = null
    protected fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_data_ttd)
        ButterKnife.inject(this)
        val i: Intent = getIntent()
        val arrImg: ByteArray = i.getExtras().getByteArray("gambar")
        tvArray.setText(Tampil.tampilArray(arrImg))
        ivFull!!.setImageBitmap(arrToBitmap(arrImg))
    }

    private fun arrToBitmap(arr: ByteArray): Bitmap {
        val bm: Bitmap = Bitmap.createBitmap(200, 200, Bitmap.Config.ARGB_8888)
        var c = 0
        var color: Int
        for (i in 0 until bm.getHeight() - 1) {
            for (j in 0 until bm.getWidth()) {
                color = if (arr[c] == 0) Color.argb(255, 255, 255, 255) else Color.argb(255, 0, 0, 0)
                bm.setPixel(j, i, color)
                c++
            }
        }
        return bm
    }
}