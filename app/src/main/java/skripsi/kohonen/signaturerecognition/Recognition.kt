package skripsi.kohonen.signaturerecognition

import android.util.Log
import butterknife.InjectView
import java.util.*

class Recognition(private val db: Database?) {
    private val kh: Kohonen?
    fun hapusSemua() {
        db!!.hapusSemua()
        kh?.hapusSemua()
    }

    @InjectView(R.id.drawing_simpan)
    var drawing: FiturDrawing? = null

    @InjectView(R.id.drawing_uji)
    var drawing2: FiturDrawing? = null
    fun setDrawing(drawing: FiturDrawing?) {
        this.drawing = drawing
        drawing2 = drawing
    }

    fun ambilGambarDrawing_simpan(): Bitmap {
        drawing!!.isDrawingCacheEnabled = true
        drawing!!.buildDrawingCache(true)
        val gambar: Bitmap = Bitmap.createBitmap(drawing!!.drawingCache)
        drawing!!.isDrawingCacheEnabled = false
        return gambar
    }

    var cekArr = true
    fun ambilGambarDrawing_uji(): Bitmap {
        drawing2!!.isDrawingCacheEnabled = true
        drawing2!!.buildDrawingCache(true)
        val gambar2: Bitmap = Bitmap.createBitmap(drawing2!!.drawingCache)
        drawing2!!.isDrawingCacheEnabled = false
        return gambar2
    }

    fun tes(): ArrayList<String?>? {
        val gambar2: Bitmap = ImageProcessing.Companion.proses(ambilGambarDrawing_uji())
        val arrImage: ByteArray = ImageProcessing.Companion.getArrImage(gambar2)
        val i = kh!!.tesDataBaru(arrImage)
        ambilJarakMaksimal(i!![0])
        return i
    }

    fun ambilJarakMaksimal(nama: String?) {
        var jarak = 0.0
        var hasil = 0.0
        val arrTtd = db!!.ambilDataBerdasarkanNama(nama)
        val arrBiner = Array(arrTtd!!.size) { ByteArray(40000) }
        val arr: ArrayList<Double?>?
        /*Log.d("size: ",String.valueOf(arrTtd.size()));*/for (i in arrTtd.indices) {
            arrBiner[i] = TandaTangan.Companion.strToArr(arrTtd[i]!!.nilai)
        }
        arr = kh!!.tesBerdasarkanNama(arrBiner)
        nilaiMax = maksimal(arr)
        jarak = ((25000 - nilaiMax) / 0.65 - 25000) * -1
        hasil = (25000 - Kohonen.Companion.minimal) / (25000 - jarak) * 100
        Log.d("hasil: ", hasil.toString())
        if (hasil < 0) {
            Kohonen.Companion.asli = 0.0
        } else if (hasil > 100) {
            Kohonen.Companion.asli = 100.0
        } else Kohonen.Companion.asli = hasil
        Log.d("nilai max", nilaiMax.toString())
        //return jarak;
    }

    private fun maksimal(nodeArray: ArrayList<Double>?): Int {
        var max = nodeArray!![0]
        for (i in nodeArray.indices) {
            if (nodeArray[i] > max) {
                max = nodeArray[i]
            }
        }
        return max.toInt()
    }

    /*public byte[] cekuji(){
        Bitmap gambar2 = ImageProcessing.proses(ambilGambarDrawing_uji());
        byte[] arrIm = ImageProcessing.getArrImage(gambar2);
        return arrIm;
    }*/
    fun cekcoretan() {
        index = false
        val gambar2: Bitmap = ImageProcessing.Companion.proses(ambilGambarDrawing_uji())
        val arrImage: ByteArray = ImageProcessing.Companion.getArrImage(gambar2)
        for (i in arrImage.indices) {
            if (arrImage[i] == 1) {
                index = true
                break
            }
        }
    }

    fun cek() {
        index2 = false
        val gambar: Bitmap = ImageProcessing.Companion.proses(ambilGambarDrawing_simpan())
        val arrImg: ByteArray = ImageProcessing.Companion.getArrImage(gambar)
        for (i in arrImg.indices) {
            if (arrImg[i] == 1) {
                index2 = true
                break
            }
        }
    }

    fun simpan(str: String?) {
        val gambar: Bitmap = ImageProcessing.Companion.proses(ambilGambarDrawing_simpan())
        val arrImg: ByteArray = ImageProcessing.Companion.getArrImage(gambar)
        db!!.addttd(TandaTangan(db.lastIndex + 1, str, TandaTangan.Companion.arrToStr(arrImg)))
        kh!!.latihDataBaru(arrImg, str)
    }

    fun hapus(pos: Int) {
        db!!.deletettd(pos)
        kh!!.hapus(pos)
    }

    fun ubah(pos: Int, nama: String?) {
        val kat = db!!.getttd(pos)
        kat!!.setnama(nama)
        db.updatettd(kat)
        kh!!.ubah(pos, nama)
    }

    fun getArrDataImage(i: Int): ByteArray {
        return TandaTangan.Companion.strToArr(db!!.getttd(i).nilai)
    }

    fun getDatanama(): ArrayList<String?>? {
        return kh!!.huruf
    }

    fun tampilData() {}
    fun tampilDb() {
        val list = db!!.allttd
        for (kat in list!!) {
            Log.d("data ke-", kat!!.id.toString() + ", " + kat!!.getnama())
        }
        Log.d("selesai", "-")
    }

    companion object {
        var index = false
        var index2 = false
        var nilaiMax = 0
    }

    init {
        kh = Kohonen(db)
    }
}