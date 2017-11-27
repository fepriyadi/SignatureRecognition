package skripsi.kohonen.signaturerecognition;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import skripsi.kohonen.signaturerecognition.adapter.GridViewAdapter;

import static skripsi.kohonen.signaturerecognition.Tab4Fragment.gridViewAdapter;

public class DataTTD extends AppCompatActivity {

    @InjectView(R.id.iv_full)
    ImageView ivFull;
    @InjectView(R.id.tv_array)
    TextView tvArray;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_ttd);
        ButterKnife.inject(this);

        Intent i = getIntent();
        byte[] arrImg = i.getExtras().getByteArray("gambar");

        tvArray.setText(Tampil.tampilArray(arrImg));
        ivFull.setImageBitmap(arrToBitmap(arrImg));
    }

    private Bitmap arrToBitmap(byte[] arr) {
        Bitmap bm = Bitmap.createBitmap(200, 200, Bitmap.Config.ARGB_8888);
        int c = 0;
        int color;
        for (int i = 0; i < bm.getHeight() - 1; i++) {
            for (int j = 0; j < bm.getWidth(); j++) {
                if (arr[c] == 0) color = Color.argb(255, 255, 255, 255);
                else color = Color.argb(255, 0, 0, 0);
                bm.setPixel(j, i, color);
                c++;
            }
        }
        return bm;
    }
}
