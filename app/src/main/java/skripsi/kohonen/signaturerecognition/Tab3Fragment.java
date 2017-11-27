package skripsi.kohonen.signaturerecognition;


import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Locale;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class Tab3Fragment extends Fragment {
    MainActivity mainActivity;
    @InjectView(R.id.drawing_uji)
    FiturDrawing drawing;
    @InjectView(R.id.TVhasil)
    TextView hasil;
    boolean bTes = false;
    private Database db;
    private Kohonen kh;
    private Context context;
    TextToSpeech tts;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_tab3, container, false);

        ButterKnife.inject(this, v);
        init();
        return v;
    }

    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    private void init() {
        this.mainActivity = (MainActivity) getActivity();
        context = getContext();
        this.db = mainActivity.db;
        drawing.setOnTouchListener(drawingTouch);
    }

    private View.OnTouchListener drawingTouch = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            float touchX = event.getX();
            float touchY = event.getY();
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    drawing.getDrawPath().moveTo(touchX, touchY);
                    drawing.getDrawPath().lineTo(touchX+1, touchY+1);
                case MotionEvent.ACTION_MOVE:
                    drawing.getDrawPath().lineTo(touchX, touchY);
                    break;
                case MotionEvent.ACTION_UP:
                    drawing.getDrawCanvas().drawPath(drawing.getDrawPath(), drawing.getDrawPaint());
                    drawing.getDrawPath().reset();
                    break;
                default:
                    return false;
            }
            drawing.invalidate();
            return true;
        }
    };

    private void uji() {
        mainActivity.recog.setDrawing(drawing);
     mainActivity.recog.cekcoretan();
        if (db.getttdsCount() == 0) {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setMessage("Data Kosong! Silahkan Simpan Min 1 Tanda Tangan Terlebih Dahulu");
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface d, int arg1) {
                    d.cancel();
                }
            });
            AlertDialog alertDialog = builder.create();
            alertDialog.show();

        }
       else if (Recognition.index == false) {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setMessage("Belum ada Coretan, Silahkan Tanda Tangan");
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface d, int arg1) {
                    d.cancel();
                }
            });
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        } else {
            Toast.makeText(getActivity(), "Tanda Tangan Berhasil Diuji", Toast.LENGTH_LONG).show();
            hasil.setText("Jarak terdekat dengan Tanda Tangan " + mainActivity.recog.tes().get(0) + " ,Tingkat Kemiripan= " + (new DecimalFormat("##.##").format(kh.asli) + "%"));
      }
    }

    @OnClick({R.id.btnclear2, R.id.bt_uji/*, R.id.btnliat*/})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnclear2:
                drawing.clear();
                hasil.setText("");
                break;
            case R.id.bt_uji:
                uji();
                break;
        }
    }


}
