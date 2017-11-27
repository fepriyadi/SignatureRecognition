package skripsi.kohonen.signaturerecognition;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by fep on 29/03/2017.
 */

public class Database extends SQLiteOpenHelper {
    private static Database sInstance;

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_ttd = "Dbttd";
    private static final String TABLE_ttd = "Tablettd";
    private static final String KEY_ID = "id";
    private static final String KEY_nama = "nama";
    private static final String KEY_Nilai = "Nilai";

    public static synchronized Database getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new Database(context.getApplicationContext());
        }
        return sInstance;
    }

    private Database(Context context) {
        super(context, DATABASE_ttd, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_ttd_TABLE = "CREATE TABLE " + TABLE_ttd + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_nama + " TEXT,"
                + KEY_Nilai + " TEXT" + ")";
        db.execSQL(CREATE_ttd_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ttd);
        onCreate(db);
    }
    public void hapusSemua() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ttd);
        onCreate(db);
    }

    public void addttd(TandaTangan Ttd) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_ID, Ttd.getId());
        values.put(KEY_nama, Ttd.getnama());
        values.put(KEY_Nilai, Ttd.getNilai());

        db.insert(TABLE_ttd, null, values);
        db.close();
    }

    public int getLastIndex() {
        ArrayList<TandaTangan> listKat = getAllttd();
        if (listKat.size() == 0) {
            return 0;
        } else {
            return listKat.get(listKat.size() - 1).getId();
        }
    }

    public TandaTangan getttd(int i) {
        int id = getIndex(i);
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_ttd, new String[]{KEY_ID,
                        KEY_nama, KEY_Nilai}, KEY_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null) cursor.moveToFirst();

        TandaTangan ttd = new TandaTangan(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2));
        cursor.close();
        db.close();
        return ttd;
    }


    public ArrayList<TandaTangan> getAllttd() {
        ArrayList<TandaTangan> ttdList = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_ttd;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                TandaTangan ttd = new TandaTangan();
                ttd.setId(Integer.parseInt(cursor.getString(0)));
                ttd.setnama(cursor.getString(1));
                ttd.setNilai(cursor.getString(2));
                ttdList.add(ttd);
            } while (cursor.moveToNext());
        }
        return ttdList;
    }

    public ArrayList<TandaTangan> ambilDataBerdasarkanNama(String nm) {
        ArrayList<TandaTangan> ttdList = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.query(TABLE_ttd, new String[]{KEY_ID,
                        KEY_nama, KEY_Nilai}, KEY_nama + "=?",
                new String[]{String.valueOf(nm)}, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                TandaTangan ttd = new TandaTangan();
                ttd.setId(Integer.parseInt(cursor.getString(0)));
                ttd.setnama(cursor.getString(1));
                ttd.setNilai(cursor.getString(2));
                ttdList.add(ttd);
            } while (cursor.moveToNext());
        }
        return ttdList;
    }

    public int getttdsCount() {
        SQLiteDatabase db = this.getReadableDatabase();
        int cnt  = (int) DatabaseUtils.queryNumEntries(db, TABLE_ttd);
        db.close();
        return cnt;
    }

    public int updatettd(TandaTangan ttd) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_nama, ttd.getnama());
        values.put(KEY_Nilai, ttd.getNilai());
        return db.update(TABLE_ttd, values, KEY_ID + " = ?",
                new String[]{String.valueOf(ttd.getId())});
    }

    public void deletettd(int i) {
        int pos = getIndex(i);
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_ttd, KEY_ID + " = ?",
                new String[] {pos  + ""});
        db.close();
    }

    private int getIndex(int pos) {
        int index = 0;
        ArrayList<TandaTangan> listKat = getAllttd();
        TandaTangan kat = listKat.get(pos);
        return kat.getId();
    }

}
