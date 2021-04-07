package skripsi.kohonen.signaturerecognition

import android.content.Context
import androidx.core.app.Fragment
import android.view.View

/**
 * A simple [Fragment] subclass.
 */
class Tab1Fragment : Fragment() {
    var mainActivity: MainActivity? = null
    var context: Context? = null
    private val tts: TextToSpeech? = null
    fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                     savedInstanceState: Bundle?): View {
        // Inflate the layout for this fragment
        val v: View = inflater.inflate(R.layout.fragment_tab1, container, false)
        ButterKnife.inject(this, v)
        init()
        return v
    }

    private fun init() {
        mainActivity = getActivity() as MainActivity?
        context = getContext()
    }
}