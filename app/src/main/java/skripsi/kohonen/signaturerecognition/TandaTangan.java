package skripsi.kohonen.signaturerecognition;

/**
 * Created by fep on 29/03/2017.
 */

public class TandaTangan {
    private int id;
    private String nama;
    private String nilai;

    public TandaTangan() {
    }
    public TandaTangan(int id, String nama, String nilai) {
        this.id = id;
        this.nama = nama;
        this.nilai = nilai;
    }

    public static String arrToStr(byte[] arr) {
        String str = "";
        for (int i=0; i<arr.length; i++) {
            str += arr[i];
        }
        return str;
    }

    public static byte[] strToArr(String str) {
        byte[] arr = new byte[str.length()];
        for (int i=0; i<arr.length; i++) {
            arr[i] = (byte) Character.getNumericValue(str.charAt(i));
        }
        return arr;
    }

    public void setId(int id) {
        this.id = id;
    }

        public void setnama(String nama) {
            this.nama = nama;
        }
        public void setNilai(String nilai) {
            this.nilai = nilai;
        }

    public int getId() {
        return id;
    }
    public String getNilai() {
        return nilai;
    }
    public String getnama() {
        return nama;
    }
}
