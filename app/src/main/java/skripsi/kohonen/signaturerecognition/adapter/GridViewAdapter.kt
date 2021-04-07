package skripsi.kohonen.signaturerecognition.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import java.util.*

/**
 * Created by fep on 29/03/2017.
 */
class GridViewAdapter(private val arrHuruf: ArrayList<String>) : BaseAdapter() {
    override fun getCount(): Int {
        return arrHuruf.size
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItem(position: Int): String {
        return arrHuruf[position]
    }

    override fun getView(position: Int, convertView: View, parent: ViewGroup): View {
        var view = convertView
        if (view == null) {
            view = LayoutInflater.from(parent.context).inflate(android.R.layout.simple_list_item_1, parent, false)
        }
        val text = view.findViewById<View>(android.R.id.text1) as TextView
        text.text = arrHuruf[position]
        return view
    }
}