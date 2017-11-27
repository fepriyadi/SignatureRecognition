package skripsi.kohonen.signaturerecognition.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

import skripsi.kohonen.signaturerecognition.Tab1Fragment;
import skripsi.kohonen.signaturerecognition.Tab2Fragment;
import skripsi.kohonen.signaturerecognition.Tab3Fragment;
import skripsi.kohonen.signaturerecognition.Tab4Fragment;
import skripsi.kohonen.signaturerecognition.Tab5Fragment;
import skripsi.kohonen.signaturerecognition.Tab6Fragment;

/**
 * Created by fep on 28/03/2017.
 */

public class SectionsPagerAdapter extends FragmentPagerAdapter {
    String[] title = new String[]{
            "home", "simpan", "pengujian", "lihat data", "bantuan", "tentang"
    };

    public SectionsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = new Tab1Fragment();
                break;
            case 1:
                fragment = new Tab2Fragment();
                break;
            case 2:
                fragment = new Tab3Fragment();
                break;
            case 3:
                fragment = new Tab4Fragment();
                break;
            case 4:
                fragment = new Tab5Fragment();
                break;
            case 5:
                fragment = new Tab6Fragment();
                break;
            default:
                fragment = null;
                break;
        }

        return fragment;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return title[position];
    }

    @Override
    public int getCount() {
        return title.length;
    }
}
