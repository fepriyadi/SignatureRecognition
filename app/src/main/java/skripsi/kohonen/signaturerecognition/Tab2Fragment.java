package skripsi.kohonen.signaturerecognition;


import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class Tab2Fragment extends Fragment
{
  
  @InjectView(R.id.drawing_simpan)
  FiturDrawing drawing;
  @InjectView(R.id.btnclear)
  ImageButton btnClear;
  @InjectView(R.id.btnsimpan)
  Button btnSimpan;
  @InjectView(R.id.Editnama)
  EditText eTnama;
  TextToSpeech t1;
  @InjectView(R.id.TVnama)
  TextView TVnama;
  private Context context;
  private MainActivity mainActivity;
  
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState)
  {
    // Inflate the layout for this fragment
    View view = inflater.inflate(R.layout.fragment_tab2, container, false);
    ButterKnife.inject(this, view);
    init();
    
    return view;
  }
  
  private void simpan()
  {
    mainActivity.recog.setDrawing(drawing);
    Log.d("nama ", eTnama.getText().toString());
    mainActivity.recog.cek();
    if (Recognition.index2 == false)
    {
      AlertDialog.Builder builder = new AlertDialog.Builder(context);
      builder.setMessage("Belum ada Coretan, Silahkan Tanda Tangan");
      builder.setPositiveButton("OK", new DialogInterface.OnClickListener()
      {
        @Override
        public void onClick(DialogInterface d, int arg1)
        {
          d.cancel();
        }
      });
      AlertDialog alertDialog = builder.create();
      alertDialog.show();
    }
    else if (eTnama.getText().toString().equals(""))
    {
      AlertDialog.Builder builder = new AlertDialog.Builder(context);
      builder.setMessage("Belum Ada Nama, Silahkan Isi Nama Terlebih dahulu");
      builder.setPositiveButton("OK", new DialogInterface.OnClickListener()
      {
        @Override
        public void onClick(DialogInterface d, int arg1)
        {
          d.cancel();
        }
      });
      AlertDialog alertDialog = builder.create();
      alertDialog.show();
    }
    else
    {
      String nama = eTnama.getText().toString();
      mainActivity.recog.simpan(nama);
      Toast.makeText(getActivity(), "Tanda Tangan Telah Tersimpan", Toast.LENGTH_LONG).show();
    }
  }
  
  private void init()
  {
    this.mainActivity = (MainActivity) getActivity();
    context = getContext();
    drawing.setOnTouchListener(drawingTouch);
    
  }
  
  private View.OnTouchListener drawingTouch = new View.OnTouchListener()
  {
    @Override
    public boolean onTouch(View v, MotionEvent event)
    {
      float touchX = event.getX();
      float touchY = event.getY();
      switch (event.getAction())
      {
        case MotionEvent.ACTION_DOWN:
          drawing.getDrawPath().moveTo(touchX, touchY);
          drawing.getDrawPath().lineTo(touchX + 1, touchY + 1);
          break;
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
  
  /* private void Texto() {
       init();

       String toSpeak = eTnama.getText().toString();
       Toast.makeText(getActivity(), toSpeak, Toast.LENGTH_SHORT).show();
       t1.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null);
   }
*/
  public void onPause()
  {
    if (t1 != null)
    {
      t1.stop();
      t1.shutdown();
    }
    super.onPause();
  }
  
  @OnClick({R.id.btnclear, R.id.btnsimpan})
  public void onClick(View view)
  {
    switch (view.getId())
    {
      case R.id.btnclear:
        drawing.clear();
        break;
      case R.id.btnsimpan:
        simpan();
        break;
    }
  }
  
  @Override
  public void onDestroyView()
  {
    super.onDestroyView();
    ButterKnife.reset(this);
  }
}
