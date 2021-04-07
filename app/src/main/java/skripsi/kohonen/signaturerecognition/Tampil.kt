package skripsi.kohonen.signaturerecognition

import java.util.*

object Tampil {
    fun tampilListInteger(arr: ArrayList<Int>, e: Int): String {
        var str = ""
        for (i in arr.indices) {
            if (e != 0) if (i % e == 0) str += "\n"
            str += arr[i].toString() + " " + " "
        }
        str = str.trim { it <= ' ' }
        return str
    }

    fun tampilListString(arr: ArrayList<String>, e: Int): String {
        var str = ""
        for (i in arr.indices) {
            if (e != 0) if (i % e == 0) str += "\n"
            str += arr[i] + " "
        }
        str = str.trim { it <= ' ' }
        return str
    }

    fun tampilListDouble(arr: ArrayList<Double>, e: Int): String {
        var str = ""
        for (i in arr.indices) {
            if (e != 0) if (i % e == 0) str += "\n"
            str += arr[i].toString() + " "
        }
        str = str.trim { it <= ' ' }
        return str
    }

    fun tampilArray(arr: ByteArray): String {
        var str = ""
        for (i in arr.indices) {
            if (i % 200 == 0) str += "\n"
            str += arr[i].toString() + " "
        }
        str = str.trim { it <= ' ' }
        return str
    }

    fun tampilArray(arr: Array<Int>): String {
        var str = ""
        for (i in arr.indices) {
            if (i % 20 == 0) str += "\n"
            str += arr[i].toString() + " "
        }
        str = str.trim { it <= ' ' }
        return str
    }

    fun tampilArray(arr: DoubleArray): String {
        var str = ""
        for (i in arr.indices) {
            if (i % 20 == 0) str += "\n"
            str += arr[i].toString() + " "
        }
        str = str.trim { it <= ' ' }
        return str
    }

    fun singkat(db: Double): String {
        val str = java.lang.Double.toString(db)
        return String.format("%1$.2f", db)
    }

    fun tampilArray2(arr: Array<DoubleArray>): String {
        var str = ""
        for (j in arr.indices) for (i in 0 until arr[0].length) {
            if (i % 20 == 0) str += "\n"
            str += singkat(arr[j][i]) + " "
        }
        str += "\n\n"
        str = str.trim { it <= ' ' }
        return str
    }

    fun tampilArray(arr: Array<String>): String {
        var str = ""
        for (i in arr.indices) {
            if (i % 9 == 0) str += "\n"
            str += arr[i] + " "
        }
        str = str.trim { it <= ' ' }
        return str
    }

    fun tampilArray(arr: IntArray): String {
        var str = ""
        for (i in arr.indices) {
            if (i % 9 == 0) str += "\n"
            str += arr[i].toString() + " "
        }
        str = str.trim { it <= ' ' }
        return str
    }
}