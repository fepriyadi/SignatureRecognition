package skripsi.kohonen.signaturerecognition;

import android.graphics.Bitmap;
import android.util.Log;

import java.util.ArrayList;

import butterknife.InjectView;

public class Recognition {
    private Database db;
    private Kohonen kh;
    public static boolean index= false;
    public static boolean index2= false;


    public Recognition(Database db) {
        this.db = db;
        kh = new Kohonen(db);

    }

    public void hapusSemua() {
        db.hapusSemua();
        if (kh != null) {
            kh.hapusSemua();
        }
    }

    @InjectView(R.id.drawing_simpan)
    FiturDrawing drawing;
    @InjectView(R.id.drawing_uji)
    FiturDrawing drawing2;
    public void setDrawing(FiturDrawing drawing) {
        this.drawing = drawing;
        this.drawing2= drawing;
    }

    public Bitmap ambilGambarDrawing_simpan() {
        drawing.setDrawingCacheEnabled(true);
        drawing.buildDrawingCache(true);
        Bitmap gambar = Bitmap.createBitmap(drawing.getDrawingCache());
        drawing.setDrawingCacheEnabled(false);
        return gambar;
    }
    public boolean cekArr = true;
    public Bitmap ambilGambarDrawing_uji() {

        drawing2.setDrawingCacheEnabled(true);
        drawing2.buildDrawingCache(true);
        Bitmap gambar2 = Bitmap.createBitmap(drawing2.getDrawingCache());
        drawing2.setDrawingCacheEnabled(false);
        return gambar2;
    }

    public ArrayList<String>  tes() {
       Bitmap gambar2 = ImageProcessing.proses(ambilGambarDrawing_uji());
        byte[] arrImage = ImageProcessing.getArrImage(gambar2);

        ArrayList<String> i = kh.tesDataBaru(arrImage);
        ambilJarakMaksimal(i.get(0));
        return i;
    }

    public static int nilaiMax = 0;
    public void ambilJarakMaksimal(String nama) {
        double jarak = 0;
        double hasil= 0;
        ArrayList<TandaTangan> arrTtd = db.ambilDataBerdasarkanNama(nama);
        byte[][] arrBiner = new byte[arrTtd.size()][40000];
        ArrayList<Double> arr;
        /*Log.d("size: ",String.valueOf(arrTtd.size()));*/
        for (int i = 0; i<arrTtd.size(); i++) {
            arrBiner[i] = TandaTangan.strToArr(arrTtd.get(i).getNilai());
        }
        arr = kh.tesBerdasarkanNama(arrBiner);

        nilaiMax = maksimal(arr);
        jarak = ((25000-nilaiMax)/(0.65)-25000)*-1;
        hasil = (25000-Kohonen.minimal)/(25000-jarak)*100;
        Log.d("hasil: ", String.valueOf(hasil));
        if (hasil <0) {
            Kohonen.asli = 0;
        }else if (hasil >100){
            Kohonen.asli = 100;
        }else
        Kohonen.asli = hasil;
        Log.d("nilai max", String.valueOf(nilaiMax));
        //return jarak;
    }

    private int maksimal(ArrayList<Double> nodeArray) {
        double max = nodeArray.get(0);
        for (int i = 0; i < nodeArray.size(); i++) {
            if (nodeArray.get(i) > max) {
                max = nodeArray.get(i);
            }
        }
        return (int) max;
    }


    /*public byte[] cekuji(){
        Bitmap gambar2 = ImageProcessing.proses(ambilGambarDrawing_uji());
        byte[] arrIm = ImageProcessing.getArrImage(gambar2);
        return arrIm;
    }*/
    public void cekcoretan(){
        index = false;
        Bitmap gambar2 = ImageProcessing.proses(ambilGambarDrawing_uji());
        byte[] arrImage = ImageProcessing.getArrImage(gambar2);
        for (int i=0; i<arrImage.length; i++) {
            if (arrImage[i] == 1) {
                index = true;
                break;
            }
        }

    }
public void cek(){
    index2 =false;
    Bitmap gambar = ImageProcessing.proses(ambilGambarDrawing_simpan());
    byte[] arrImg = ImageProcessing.getArrImage(gambar);
    for (int i=0; i<arrImg.length; i++) {
        if (arrImg[i] == 1) {
            index2 = true;
            break;
        }
    }
}
    public void simpan(String str) {

        Bitmap gambar = ImageProcessing.proses(ambilGambarDrawing_simpan());
        byte[] arrImg = ImageProcessing.getArrImage(gambar);
        db.addttd(new TandaTangan(db.getLastIndex()+1, str, TandaTangan.arrToStr(arrImg)));
        kh.latihDataBaru(arrImg, str);
    }

    public void hapus(int pos) {
        db.deletettd(pos);
        kh.hapus(pos);
    }

    public void ubah(int pos, String nama) {
        TandaTangan kat = db.getttd(pos);
        kat.setnama(nama);
        db.updatettd(kat);
        kh.ubah(pos, nama);
    }

    public byte[] getArrDataImage(int i) {
        return TandaTangan.strToArr(db.getttd(i).getNilai());
    }

    public ArrayList<String> getDatanama() {
        return kh.huruf;
    }

    public void tampilData() {


    }

    public void tampilDb() {
        ArrayList<TandaTangan> list = db.getAllttd();
        for (TandaTangan kat : list) {
            Log.d("data ke-", kat.getId() + ", " + kat.getnama());
        }
        Log.d("selesai", "-");
    }
}