package skripsi.kohonen.signaturerecognition.adapter

import androidx.core.app.Fragment

/**
 * Created by fep on 28/03/2017.
 */
class SectionsPagerAdapter(fm: FragmentManager?) : FragmentPagerAdapter(fm) {
    var title = arrayOf(
            "home", "simpan", "pengujian", "lihat data", "bantuan", "tentang"
    )

    fun getItem(position: Int): Fragment? {
        var fragment: Fragment? = null
        when (position) {
            0 -> fragment = Tab1Fragment()
            1 -> fragment = Tab2Fragment()
            2 -> fragment = Tab3Fragment()
            3 -> fragment = Tab4Fragment()
            4 -> fragment = Tab5Fragment()
            5 -> fragment = Tab6Fragment()
            else -> fragment = null
        }
        return fragment
    }

    fun getPageTitle(position: Int): CharSequence {
        return title[position]
    }

    fun getCount(): Int {
        return title.size
    }
}