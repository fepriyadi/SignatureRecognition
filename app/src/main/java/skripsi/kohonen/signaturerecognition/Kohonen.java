package skripsi.kohonen.signaturerecognition;

import android.util.Log;

import java.util.ArrayList;

public class Kohonen {

    private final int panjangPola = 40000;
    private final double alpha;
    private double[] d;
    private int panjangX;
    MainActivity mainActivity;
    private final ArrayList<byte[]> x;
    public final ArrayList<String> huruf;
    public final ArrayList<byte[]> nilai;
    public final ArrayList<String> nama;

    private final Database db;

    public Kohonen(Database db) {
        alpha = 0.6;
        panjangX = 1;
        this.db = db;
        d = new double[panjangX];
        huruf = new ArrayList<>();
        x = new ArrayList<>();
        nama = new ArrayList<>();
        nilai = new ArrayList<>();
        isiX();
    }

    private void isiX() {
        x.clear();
        huruf.clear();
        ArrayList<TandaTangan> listttd = db.getAllttd();
        for (int i = 0; i < db.getttdsCount(); i++) {
            TandaTangan kat = listttd.get(i);
            byte[] bt = TandaTangan.strToArr(kat.getNilai());
            x.add(bt);
            String str = kat.getnama();
            huruf.add(str);
        }
        panjangX = x.size();
    }

    public static double asli = 0;
    public static double minimal = 0;

    public static int nilaiMaxJarak = 0;
    public static double nilaiJarak = 0;
    public  static  String name ="";
    private void hitungX(byte[] arr) {
        d = new double[panjangX];
        double minjarak = 0;
        double maxJarak = 0;

        clearArray(d);
        for (int i = 0; i < panjangX; i++) {
            for (int j = 0; j < panjangPola; j++) {
                d[i] += Math.pow((x.get(i)[j] - arr[j]), 2);
            }
            if (i == 0) {
                minjarak = d[0];
            } else if ((i > 0) && (d[i] < minjarak)) {
                minjarak = d[i];
            }
/*
            if (i == 0 ) {
                maxJarak = d[0];
            } else if ((i > 0) && (d[i] > maxJarak)) {
                maxJarak = d[i];
            }*/

            Log.d("jarak cluster:" + (i), String.valueOf(d[i]));
        }

       /* nilaiMaxJarak = (int) maxJarak;*/
        Log.d("jarak terkecil: ", String.valueOf(minjarak));
        minimal = minjarak;

       /* asli = (20000 - minjarak) / (20000 - 0) * 100;
        Log.d("nilai", String.valueOf(nilai));*/
    }


    private void clearArray(double[] nodeArray) {
        for (int i = 0; i < panjangX; i++) {
            nodeArray[i] = 0.0;
        }
    }

    private int minimum(ArrayList<Double> nodeArray) {
        double min = nodeArray.get(0);
        int index = 0;
        for (int i = 0; i < nodeArray.size(); i++) {
            if (nodeArray.get(i) < min) {
                min = nodeArray.get(i);
                index = i;
            }
        }
        return index;
    }

    public ArrayList<String> tesDataBaru(byte[] arr) {
        ArrayList<String> arrStr = new ArrayList<>();
        hitungX(arr);
        ArrayList<Double> arrD = new ArrayList<>();
        ArrayList<String> arrS = new ArrayList<>();
        for (int x = 0; x < huruf.size(); x++) {
            arrD.add(d[x]);
            arrS.add(huruf.get(x));
        }
        int min = minimum(arrD);
        arrStr.add(huruf.get(min));
        name = huruf.get(min);
        Log.d("nama: ", String.valueOf((huruf.get(min))));
        return arrStr;
    }
    public ArrayList<Double> tesBerdasarkanNama(byte[][] arrBiner) {
        ArrayList<Double> arrHasil = new ArrayList<>();
        double[] jarak = new double[arrBiner.length];
        for (int i = 0; i < arrBiner.length; i++) {
            for (int j = 0; j < panjangPola; j++) {
                jarak[i] += Math.pow((arrBiner[i][j] - arrBiner[0][j]), 2);
            }
            arrHasil.add(jarak[i]);
        }
        return arrHasil;
    }

    ArrayList<Integer> sama(ArrayList<String> arrS, String str) {
        ArrayList<Integer> arrInt = new ArrayList<>();
        for (int i = 0; i < arrS.size(); i++) {
            if (arrS.get(i).equals(str)) {
                arrInt.add(i);
            }
        }
        return arrInt;
    }

    public void hapus(int pos) {
        huruf.remove(pos);
        x.remove(pos);
        panjangX = x.size();
    }

    public void ubah(int pos, String str) {
        huruf.set(pos, str);
    }

    public void hapusSemua() {
        x.clear();
        huruf.clear();
    }

    public void latihDataBaru(byte[] arr, String str) {
        byte[] bt = new byte[arr.length];
        System.arraycopy(arr, 0, bt, 0, arr.length);
        x.add(bt);
        huruf.add(str);
        panjangX = x.size();
    }
}