package skripsi.kohonen.signaturerecognition

import android.content.ContentValues
import android.content.Context
import android.database.DatabaseUtils
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import java.util.*

/**
 * Created by fep on 29/03/2017.
 */
class Database private constructor(context: Context) : SQLiteOpenHelper(context, DATABASE_ttd, null, DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase) {
        val CREATE_ttd_TABLE = ("CREATE TABLE " + TABLE_ttd + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_nama + " TEXT,"
                + KEY_Nilai + " TEXT" + ")")
        db.execSQL(CREATE_ttd_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ttd)
        onCreate(db)
    }

    fun hapusSemua() {
        val db = this.writableDatabase
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ttd)
        onCreate(db)
    }

    fun addttd(Ttd: TandaTangan) {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(KEY_ID, Ttd.id)
        values.put(KEY_nama, Ttd.getnama())
        values.put(KEY_Nilai, Ttd.nilai)
        db.insert(TABLE_ttd, null, values)
        db.close()
    }

    fun getLastIndex(): Int {
        val listKat = getAllttd()
        return if (listKat.size == 0) {
            0
        } else {
            listKat[listKat.size - 1].id
        }
    }

    fun getttd(i: Int): TandaTangan {
        val id = getIndex(i)
        val db = this.readableDatabase
        val cursor = db.query(TABLE_ttd, arrayOf(KEY_ID,
                KEY_nama, KEY_Nilai), KEY_ID + "=?", arrayOf(id.toString()), null, null, null, null)
        cursor?.moveToFirst()
        val ttd = TandaTangan(cursor!!.getString(0).toInt(),
                cursor.getString(1), cursor.getString(2))
        cursor.close()
        db.close()
        return ttd
    }

    fun getAllttd(): ArrayList<TandaTangan> {
        val ttdList = ArrayList<TandaTangan>()
        val selectQuery = "SELECT * FROM " + TABLE_ttd
        val db = this.writableDatabase
        val cursor = db.rawQuery(selectQuery, null)
        if (cursor.moveToFirst()) {
            do {
                val ttd = TandaTangan()
                ttd.id = cursor.getString(0).toInt()
                ttd.setnama(cursor.getString(1))
                ttd.nilai = cursor.getString(2)
                ttdList.add(ttd)
            } while (cursor.moveToNext())
        }
        return ttdList
    }

    fun ambilDataBerdasarkanNama(nm: String?): ArrayList<TandaTangan> {
        val ttdList = ArrayList<TandaTangan>()
        val db = this.writableDatabase
        val cursor = db.query(TABLE_ttd, arrayOf(KEY_ID,
                KEY_nama, KEY_Nilai), KEY_nama + "=?", arrayOf(nm.toString()), null, null, null, null)
        if (cursor.moveToFirst()) {
            do {
                val ttd = TandaTangan()
                ttd.id = cursor.getString(0).toInt()
                ttd.setnama(cursor.getString(1))
                ttd.nilai = cursor.getString(2)
                ttdList.add(ttd)
            } while (cursor.moveToNext())
        }
        return ttdList
    }

    fun getttdsCount(): Int {
        val db = this.readableDatabase
        val cnt = DatabaseUtils.queryNumEntries(db, TABLE_ttd).toInt()
        db.close()
        return cnt
    }

    fun updatettd(ttd: TandaTangan?): Int {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(KEY_nama, ttd!!.getnama())
        values.put(KEY_Nilai, ttd.nilai)
        return db.update(TABLE_ttd, values, KEY_ID + " = ?", arrayOf(ttd.id.toString()))
    }

    fun deletettd(i: Int) {
        val pos = getIndex(i)
        val db = this.writableDatabase
        db.delete(TABLE_ttd, KEY_ID + " = ?", arrayOf(pos.toString() + ""))
        db.close()
    }

    private fun getIndex(pos: Int): Int {
        val index = 0
        val listKat = getAllttd()
        val kat = listKat[pos]
        return kat.id
    }

    companion object {
        private var sInstance: Database? = null
        private const val DATABASE_VERSION = 1
        private const val DATABASE_ttd = "Dbttd"
        private const val TABLE_ttd = "Tablettd"
        private const val KEY_ID = "id"
        private const val KEY_nama = "nama"
        private const val KEY_Nilai = "Nilai"
        @Synchronized
        fun getInstance(context: Context): Database? {
            if (sInstance == null) {
                sInstance = Database(context.applicationContext)
            }
            return sInstance
        }
    }
}