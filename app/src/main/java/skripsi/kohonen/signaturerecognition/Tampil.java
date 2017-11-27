package skripsi.kohonen.signaturerecognition;

import android.util.Log;

import java.util.ArrayList;

public class Tampil {
    public static String tampilListInteger(ArrayList<Integer> arr, int e) {
        String str = "";
        for (int i=0; i<arr.size(); i++) {
            if (e != 0)
                if (i%e == 0) str += "\n";
            str += arr.get(i) + " "+" ";
        }
        str = str.trim();
        return str;
    }

    public static String tampilListString(ArrayList<String> arr, int e) {
        String str = "";
        for (int i=0; i<arr.size(); i++) {
            if (e != 0)
                if (i%e == 0) str += "\n";
            str += arr.get(i) + " ";
        }
        str = str.trim();
        return str;
    }

    public static String tampilListDouble(ArrayList<Double> arr, int e) {
        String str = "";
        for (int i=0; i<arr.size(); i++) {
            if (e != 0)
                if (i%e == 0) str += "\n";
            str += arr.get(i) + " ";
        }
        str = str.trim();
        return str;
    }

    public static String tampilArray(byte[] arr) {
        String str = "";
        for (int i = 0; i < arr.length; i++) {
            if (i % 200 == 0) str += "\n";
            str += arr[i] + " ";
        }
        str = str.trim();
        return str;
    }

    public static String tampilArray(Integer[] arr) {
        String str = "";
        for (int i = 0; i < arr.length; i++) {
            if (i % 20 == 0) str += "\n";
            str += arr[i] + " ";
        }
        str = str.trim();
        return str;
    }

    public static String tampilArray(double[] arr) {
        String str = "";
        for (int i = 0; i < arr.length; i++) {
            if (i % 20 == 0) str += "\n";
            str += arr[i] + " ";
        }
        str = str.trim();
        return str;
    }

    public static String singkat(double db) {
        String str = Double.toString(db);
        String str2 = String.format("%1$.2f", db);
        return str2;
    }

    public static String tampilArray2(double[][] arr) {
        String str = "";
        for (int j= 0; j< arr.length; j++)
            for (int i = 0; i < arr[0].length; i++) {
                if (i % 20 == 0) str += "\n";
                str += singkat(arr[j][i]) + " ";
            }
        str += "\n\n";
        str = str.trim();
        return str;
    }

    public static String tampilArray(String[] arr) {
        String str = "";
        for (int i = 0; i < arr.length; i++) {
            if (i % 9 == 0) str += "\n";
            str += arr[i] + " ";
        }
        str = str.trim();
        return str;
    }

    public static String tampilArray(int[] arr) {
        String str = "";
        for (int i = 0; i < arr.length; i++) {
            if (i % 9 == 0) str += "\n";
            str += arr[i] + " ";
        }
        str = str.trim();
        return str;
    }
}
