package skripsi.kohonen.signaturerecognition

import android.content.Context
import androidx.core.app.Fragment
import android.view.View
import java.text.DecimalFormat

/**
 * A simple [Fragment] subclass.
 */
class Tab3Fragment : Fragment() {
    var mainActivity: MainActivity? = null

    @InjectView(R.id.drawing_uji)
    var drawing: FiturDrawing? = null

    @InjectView(R.id.TVhasil)
    var hasil: TextView? = null
    var bTes = false
    private var db: Database? = null
    private val kh: Kohonen? = null
    private var context: Context? = null
    var tts: TextToSpeech? = null
    fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                     savedInstanceState: Bundle?): View {
        // Inflate the layout for this fragment
        val v: View = inflater.inflate(R.layout.fragment_tab3, container, false)
        ButterKnife.inject(this, v)
        init()
        return v
    }

    fun onDestroyView() {
        super.onDestroyView()
        ButterKnife.reset(this)
    }

    private fun init() {
        mainActivity = getActivity() as MainActivity?
        context = getContext()
        db = mainActivity!!.db
        drawing!!.setOnTouchListener(drawingTouch)
    }

    private val drawingTouch: OnTouchListener = object : OnTouchListener {
        override fun onTouch(v: View, event: MotionEvent): Boolean {
            val touchX: Float = event.getX()
            val touchY: Float = event.getY()
            when (event.getAction()) {
                MotionEvent.ACTION_DOWN -> {
                    drawing!!.drawPath!!.moveTo(touchX, touchY)
                    drawing!!.drawPath!!.lineTo(touchX + 1, touchY + 1)
                    drawing!!.drawPath!!.lineTo(touchX, touchY)
                }
                MotionEvent.ACTION_MOVE -> drawing!!.drawPath!!.lineTo(touchX, touchY)
                MotionEvent.ACTION_UP -> {
                    drawing!!.getDrawCanvas()!!.drawPath(drawing!!.drawPath!!, drawing!!.drawPaint!!)
                    drawing!!.drawPath!!.reset()
                }
                else -> return false
            }
            drawing!!.invalidate()
            return true
        }
    }

    private fun uji() {
        mainActivity!!.recog!!.setDrawing(drawing)
        mainActivity!!.recog!!.cekcoretan()
        if (db!!.getttdsCount() == 0) {
            val builder: AlertDialog.Builder = Builder(context)
            builder.setMessage("Data Kosong! Silahkan Simpan Min 1 Tanda Tangan Terlebih Dahulu")
            builder.setPositiveButton("OK", object : DialogInterface.OnClickListener {
                override fun onClick(d: DialogInterface, arg1: Int) {
                    d.cancel()
                }
            })
            val alertDialog: AlertDialog = builder.create()
            alertDialog.show()
        } else if (Recognition.Companion.index == false) {
            val builder: AlertDialog.Builder = Builder(context)
            builder.setMessage("Belum ada Coretan, Silahkan Tanda Tangan")
            builder.setPositiveButton("OK", object : DialogInterface.OnClickListener {
                override fun onClick(d: DialogInterface, arg1: Int) {
                    d.cancel()
                }
            })
            val alertDialog: AlertDialog = builder.create()
            alertDialog.show()
        } else {
            Toast.makeText(getActivity(), "Tanda Tangan Berhasil Diuji", Toast.LENGTH_LONG).show()
            hasil.setText("Jarak terdekat dengan Tanda Tangan " + mainActivity!!.recog!!.tes()!![0] + " ,Tingkat Kemiripan= " + (DecimalFormat("##.##").format(Kohonen.Companion.asli) + "%"))
        }
    }

    @OnClick(R.id.btnclear2, R.id.bt_uji /*, R.id.btnliat*/)
    fun onClick(v: View) {
        when (v.id) {
            R.id.btnclear2 -> {
                drawing!!.clear()
                hasil.setText("")
            }
            R.id.bt_uji -> uji()
        }
    }
}