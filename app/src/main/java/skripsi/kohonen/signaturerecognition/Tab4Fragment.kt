package skripsi.kohonen.signaturerecognition

import android.content.Context
import androidx.core.app.Fragment
import android.view.View
import android.widget.Button

/**
 * A simple [Fragment] subclass.
 */
class Tab4Fragment : Fragment() {
    @InjectView(R.id.grview_data)
    var grviewData: GridView? = null

    @InjectView(R.id.bt_hapus_semua)
    var hapus_semua: Button? = null
    private var mainActivity: MainActivity? = null
    private var context: Context? = null
    fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                     savedInstanceState: Bundle?): View {
        val view: View = inflater.inflate(R.layout.fragment_tab4, container, false)
        ButterKnife.inject(this, view)
        init()
        return view
    }

    private fun init() {
        mainActivity = getActivity() as MainActivity?
        context = getContext()
        gridViewAdapter = GridViewAdapter(mainActivity!!.recog!!.datanama)
        grviewData.setAdapter(gridViewAdapter)
        grviewData.setOnItemClickListener(handleItemClick)
    }

    @OnClick(R.id.bt_hapus_semua)
    fun onClick(view: View) {
        when (view.id) {
            R.id.bt_hapus_semua -> tampilDialog()
        }
    }

    private fun tampilDialog2(position: Int) {
        val builder: AlertDialog.Builder = Builder(context)
        builder.setMessage("Hapus Tanda Tangan?")
        builder.setPositiveButton("Hapus", object : DialogInterface.OnClickListener {
            override fun onClick(d: DialogInterface, arg1: Int) {
                mainActivity!!.recog!!.hapus(position)
                gridViewAdapter.notifyDataSetChanged()
                Toast.makeText(context, "Tanda Tangan telah dihapus", Toast.LENGTH_SHORT).show()
            }
        })
        builder.setNegativeButton("Batal", object : DialogInterface.OnClickListener {
            override fun onClick(dialog: DialogInterface, which: Int) {
                dialog.cancel()
            }
        })
        val alertDialog: AlertDialog = builder.create()
        alertDialog.show()
    }

    private val handleItemClick: OnItemClickListener = object : OnItemClickListener {
        override fun onItemClick(parent: AdapterView<*>?, view: View, position: Int, id: Long) {
            val builder: AlertDialog.Builder = Builder(context)
            builder.setTitle("Pilih")
            builder.setItems(arrayOf<CharSequence>("Lihat data",  /* "Ubah",*/"Hapus", "Cancel"),
                    object : DialogInterface.OnClickListener {
                        override fun onClick(dialog: DialogInterface, which: Int) {
                            when (which) {
                                0 -> {
                                    val i = Intent(mainActivity.getApplicationContext(), DataTTD::class.java)
                                    i.putExtra("gambar", mainActivity!!.recog!!.getArrDataImage(position))
                                    startActivity(i)
                                }
                                1 -> tampilDialog2(position)
                                2 -> dialog.cancel()
                            }
                        }
                    })
            val alertDialog: AlertDialog = builder.create()
            alertDialog.show()
        }
    }

    fun onDestroyView() {
        super.onDestroyView()
        ButterKnife.reset(this)
    }

    private fun tampilDialog() {
        val builder: AlertDialog.Builder = Builder(context)
        builder.setMessage("Anda yakin ingin menghapus semua data?")
        builder.setPositiveButton("Hapus", object : DialogInterface.OnClickListener {
            override fun onClick(d: DialogInterface, arg1: Int) {
                mainActivity!!.recog!!.hapusSemua()
                gridViewAdapter.notifyDataSetChanged()
                Toast.makeText(context, "semua data telah dihapus", Toast.LENGTH_SHORT).show()
            }
        })
        builder.setNegativeButton("batal", object : DialogInterface.OnClickListener {
            override fun onClick(dialog: DialogInterface, which: Int) {
                dialog.cancel()
            }
        })
        val alertDialog: AlertDialog = builder.create()
        alertDialog.show()
    } /* private void tampilDialogubah(final int position) {
        mainActivity.ubah = true;
        mainActivity.posisiUbah = position;
        mainActivity.hurufUbah = Recognition.ARR_KATAKANA[position];
        getFragmentManager().popBackStack();

        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage("ubah?");
        builder.setPositiveButton("UBAH", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface d, int arg1) {
                mainActivity.ubah = false;
                mainActivity.recog.ubah(mainActivity.posisiUbah, mainActivity.hurufUbah);

                DataFragment.gridViewDataAdapter.notifyDataSetChanged();
                Toast.makeText(context,"huruf yang diubah " + position,Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }*/

    companion object {
        var gridViewAdapter: GridViewAdapter? = null
    }
}