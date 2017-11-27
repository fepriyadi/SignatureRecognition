package skripsi.kohonen.signaturerecognition.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by fep on 29/03/2017.
 */

public class GridViewAdapter extends BaseAdapter {
    private ArrayList<String> arrHuruf;

    public GridViewAdapter(ArrayList<String> arrHuruf) {
        this.arrHuruf = arrHuruf;
    }

    @Override
    public int getCount() {
        return arrHuruf.size();
    }

    @Override
    public long getItemId(final int position) {
        return position;
    }

    @Override
    public String getItem(final int position) {
        return arrHuruf.get(position);
    }

    @Override
    public View getView(final int position, final View convertView, final ViewGroup parent) {

        View view = convertView;

        if (view == null) {
            view = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);
        }
        final TextView text = (TextView) view.findViewById(android.R.id.text1);
        text.setText(arrHuruf.get(position));

        return view;
    }
}
