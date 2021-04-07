package skripsi.kohonen.signaturerecognition

/**
 * Created by fep on 29/03/2017.
 */
class TandaTangan {
    private var id = 0
    private var nama: String? = null
    private var nilai: String? = null

    constructor() {}
    constructor(id: Int, nama: String?, nilai: String?) {
        this.id = id
        this.nama = nama
        this.nilai = nilai
    }

    fun setId(id: Int) {
        this.id = id
    }

    fun setnama(nama: String?) {
        this.nama = nama
    }

    fun setNilai(nilai: String?) {
        this.nilai = nilai
    }

    fun getId(): Int {
        return id
    }

    fun getNilai(): String? {
        return nilai
    }

    fun getnama(): String? {
        return nama
    }

    companion object {
        fun arrToStr(arr: ByteArray): String {
            var str = ""
            for (i in arr.indices) {
                str += arr[i]
            }
            return str
        }

        fun strToArr(str: String?): ByteArray {
            val arr = ByteArray(str!!.length)
            for (i in arr.indices) {
                arr[i] = Character.getNumericValue(str[i]).toByte()
            }
            return arr
        }
    }
}