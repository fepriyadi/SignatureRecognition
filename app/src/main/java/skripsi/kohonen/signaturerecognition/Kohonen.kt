package skripsi.kohonen.signaturerecognition

import android.util.Log
import java.util.*

class Kohonen(private val db: Database?) {
    private val panjangPola = 40000
    private val alpha = 0.6
    private var d: DoubleArray
    private var panjangX = 1
    var mainActivity: MainActivity? = null
    private val x: ArrayList<ByteArray>
    val huruf: ArrayList<String?>
    val nilai: ArrayList<ByteArray>
    val nama: ArrayList<String>
    private fun isiX() {
        x.clear()
        huruf.clear()
        val listttd = db!!.allttd
        for (i in 0 until db.getttdsCount()) {
            val kat = listttd!![i]
            val bt: ByteArray = TandaTangan.Companion.strToArr(kat!!.nilai)
            x.add(bt)
            val str = kat!!.getnama()
            huruf.add(str)
        }
        panjangX = x.size
    }

    private fun hitungX(arr: ByteArray) {
        d = DoubleArray(panjangX)
        var minjarak = 0.0
        val maxJarak = 0.0
        clearArray(d)
        for (i in 0 until panjangX) {
            for (j in 0 until panjangPola) {
                d[i] += Math.pow((x[i][j] - arr[j]).toDouble(), 2.0)
            }
            if (i == 0) {
                minjarak = d[0]
            } else if (i > 0 && d[i] < minjarak) {
                minjarak = d[i]
            }
            /*
            if (i == 0 ) {
                maxJarak = d[0];
            } else if ((i > 0) && (d[i] > maxJarak)) {
                maxJarak = d[i];
            }*/Log.d("jarak cluster:$i", d[i].toString())
        }

        /* nilaiMaxJarak = (int) maxJarak;*/Log.d("jarak terkecil: ", minjarak.toString())
        minimal = minjarak

        /* asli = (20000 - minjarak) / (20000 - 0) * 100;
        Log.d("nilai", String.valueOf(nilai));*/
    }

    private fun clearArray(nodeArray: DoubleArray) {
        for (i in 0 until panjangX) {
            nodeArray[i] = 0.0
        }
    }

    private fun minimum(nodeArray: ArrayList<Double>): Int {
        var min = nodeArray[0]
        var index = 0
        for (i in nodeArray.indices) {
            if (nodeArray[i] < min) {
                min = nodeArray[i]
                index = i
            }
        }
        return index
    }

    fun tesDataBaru(arr: ByteArray): ArrayList<String?> {
        val arrStr = ArrayList<String?>()
        hitungX(arr)
        val arrD = ArrayList<Double>()
        val arrS = ArrayList<String?>()
        for (x in huruf.indices) {
            arrD.add(d[x])
            arrS.add(huruf[x])
        }
        val min = minimum(arrD)
        arrStr.add(huruf[min])
        name = huruf[min]
        Log.d("nama: ", huruf[min].toString())
        return arrStr
    }

    fun tesBerdasarkanNama(arrBiner: Array<ByteArray>): ArrayList<Double> {
        val arrHasil = ArrayList<Double>()
        val jarak = DoubleArray(arrBiner.size)
        for (i in arrBiner.indices) {
            for (j in 0 until panjangPola) {
                jarak[i] += Math.pow((arrBiner[i][j] - arrBiner[0][j]).toDouble(), 2.0)
            }
            arrHasil.add(jarak[i])
        }
        return arrHasil
    }

    fun sama(arrS: ArrayList<String>, str: String): ArrayList<Int> {
        val arrInt = ArrayList<Int>()
        for (i in arrS.indices) {
            if (arrS[i] == str) {
                arrInt.add(i)
            }
        }
        return arrInt
    }

    fun hapus(pos: Int) {
        huruf.removeAt(pos)
        x.removeAt(pos)
        panjangX = x.size
    }

    fun ubah(pos: Int, str: String?) {
        huruf[pos] = str
    }

    fun hapusSemua() {
        x.clear()
        huruf.clear()
    }

    fun latihDataBaru(arr: ByteArray, str: String?) {
        val bt = ByteArray(arr.size)
        System.arraycopy(arr, 0, bt, 0, arr.size)
        x.add(bt)
        huruf.add(str)
        panjangX = x.size
    }

    companion object {
        var asli = 0.0
        var minimal = 0.0
        var nilaiMaxJarak = 0
        var nilaiJarak = 0.0
        var name: String? = ""
    }

    init {
        d = DoubleArray(panjangX)
        huruf = ArrayList()
        x = ArrayList()
        nama = ArrayList()
        nilai = ArrayList()
        isiX()
    }
}