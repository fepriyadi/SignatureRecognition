package skripsi.kohonen.signaturerecognition

import android.support.design.widget.TabLayout

class MainActivity : AppCompatActivity() {
    var tabs: TabLayout? = null
    var db: Database? = null
    var recog: Recognition? = null
    var statusInput: StatusInput? = null
    private val training = false
    var ubah = false
    var posisiUbah = 0
    var hurufUbah = ""

    @InjectView(R.id.container)
    var pager: CustomViewPager? = null
    protected fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ButterKnife.inject(this)

        /*pager = (CustomViewPager) findViewById(R.id.container);*/pager!!.isPagingEnabled = false
        //        pager.setSwipeable(true);
        pager.setAdapter(SectionsPagerAdapter(getSupportFragmentManager()))
        tabs = findViewById(R.id.tabs) as TabLayout?
        tabs.setupWithViewPager(pager)
        tabs.setTabGravity(TabLayout.GRAVITY_FILL)
        db = Database.Companion.getInstance(this.getApplicationContext())
        recog = Recognition(db)
    }

    private val mTts: TextToSpeech? = null
}