package skripsi.kohonen.signaturerecognition

import android.content.Context
import androidx.core.app.Fragment
import android.util.Log
import android.view.View
import android.widget.Button

/**
 * A simple [Fragment] subclass.
 */
class Tab2Fragment : Fragment() {
    @InjectView(R.id.drawing_simpan)
    var drawing: FiturDrawing? = null

    @InjectView(R.id.btnclear)
    var btnClear: ImageButton? = null

    @InjectView(R.id.btnsimpan)
    var btnSimpan: Button? = null

    @InjectView(R.id.Editnama)
    var eTnama: EditText? = null
    var t1: TextToSpeech? = null

    @InjectView(R.id.TVnama)
    var TVnama: TextView? = null
    private var context: Context? = null
    private var mainActivity: MainActivity? = null
    fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                     savedInstanceState: Bundle?): View {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_tab2, container, false)
        ButterKnife.inject(this, view)
        init()
        return view
    }

    private fun simpan() {
        mainActivity!!.recog!!.setDrawing(drawing)
        Log.d("nama ", eTnama.getText().toString())
        mainActivity!!.recog!!.cek()
        if (Recognition.Companion.index2 == false) {
            val builder: AlertDialog.Builder = Builder(context)
            builder.setMessage("Belum ada Coretan, Silahkan Tanda Tangan")
            builder.setPositiveButton("OK", object : DialogInterface.OnClickListener {
                override fun onClick(d: DialogInterface, arg1: Int) {
                    d.cancel()
                }
            })
            val alertDialog: AlertDialog = builder.create()
            alertDialog.show()
        } else if (eTnama.getText().toString() == "") {
            val builder: AlertDialog.Builder = Builder(context)
            builder.setMessage("Belum Ada Nama, Silahkan Isi Nama Terlebih dahulu")
            builder.setPositiveButton("OK", object : DialogInterface.OnClickListener {
                override fun onClick(d: DialogInterface, arg1: Int) {
                    d.cancel()
                }
            })
            val alertDialog: AlertDialog = builder.create()
            alertDialog.show()
        } else {
            val nama: String = eTnama.getText().toString()
            mainActivity!!.recog!!.simpan(nama)
            Toast.makeText(getActivity(), "Tanda Tangan Telah Tersimpan", Toast.LENGTH_LONG).show()
        }
    }

    private fun init() {
        mainActivity = getActivity() as MainActivity?
        context = getContext()
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

    /* private void Texto() {
       init();

       String toSpeak = eTnama.getText().toString();
       Toast.makeText(getActivity(), toSpeak, Toast.LENGTH_SHORT).show();
       t1.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null);
   }
*/
    fun onPause() {
        if (t1 != null) {
            t1.stop()
            t1.shutdown()
        }
        super.onPause()
    }

    @OnClick(R.id.btnclear, R.id.btnsimpan)
    fun onClick(view: View) {
        when (view.id) {
            R.id.btnclear -> drawing!!.clear()
            R.id.btnsimpan -> simpan()
        }
    }

    fun onDestroyView() {
        super.onDestroyView()
        ButterKnife.reset(this)
    }
}