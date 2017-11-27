package skripsi.kohonen.signaturerecognition;


import android.content.Context;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Locale;

import butterknife.ButterKnife;
import butterknife.InjectView;


/**
 * A simple {@link Fragment} subclass.
 */
public class Tab1Fragment extends Fragment {
MainActivity mainActivity;
    Context context;
    private TextToSpeech tts;
    public Tab1Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_tab1, container, false);
        ButterKnife.inject(this, v);
        init();
        return v;
    }
    private void init() {
        this.mainActivity = (MainActivity) getActivity();
        context = getContext();
    }


}
