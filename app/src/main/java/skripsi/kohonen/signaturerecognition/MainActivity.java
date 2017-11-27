package skripsi.kohonen.signaturerecognition;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import butterknife.InjectView;
import skripsi.kohonen.signaturerecognition.adapter.SectionsPagerAdapter;

public class MainActivity extends AppCompatActivity {
    TabLayout tabs;
    public Database db;
    public Recognition recog;
    public StatusInput statusInput;

    private boolean training = false;

    public boolean ubah = false;
    public int posisiUbah = 0;
    public String hurufUbah = "";
    @InjectView(R.id.container)
    CustomViewPager pager;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);

        /*pager = (CustomViewPager) findViewById(R.id.container);*/
        pager.setPagingEnabled(false);
//        pager.setSwipeable(true);
        pager.setAdapter(new SectionsPagerAdapter(getSupportFragmentManager()));

        tabs = (TabLayout) findViewById(R.id.tabs);

        tabs.setupWithViewPager(pager);
        tabs.setTabGravity(TabLayout.GRAVITY_FILL);

        db = Database.getInstance(this.getApplicationContext());
        recog = new Recognition(db);

    }
    private TextToSpeech mTts;

}
