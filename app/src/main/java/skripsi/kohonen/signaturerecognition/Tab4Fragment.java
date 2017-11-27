package skripsi.kohonen.signaturerecognition;


import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import skripsi.kohonen.signaturerecognition.adapter.GridViewAdapter;


/**
 * A simple {@link Fragment} subclass.
 */
public class Tab4Fragment extends Fragment {
    @InjectView(R.id.grview_data)
    GridView grviewData;
    @InjectView((R.id.bt_hapus_semua))
    Button hapus_semua;
    private MainActivity mainActivity;
    public static GridViewAdapter gridViewAdapter;
    private Context context;

    public Tab4Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tab4, container, false);
        ButterKnife.inject(this, view);
        init();
        return view;
    }

    private void init() {
        mainActivity = (MainActivity) getActivity();
        context = getContext();
        gridViewAdapter = new GridViewAdapter(mainActivity.recog.getDatanama());
        grviewData.setAdapter(gridViewAdapter);
        grviewData.setOnItemClickListener(handleItemClick);
    }

    @OnClick({R.id.bt_hapus_semua})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_hapus_semua:
                tampilDialog();
                break;
        }
    }
    private void tampilDialog2(final int position) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage("Hapus Tanda Tangan?");
        builder.setPositiveButton("Hapus", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface d, int arg1) {
                mainActivity.recog.hapus(position);
                gridViewAdapter.notifyDataSetChanged();
                Toast.makeText(context,"Tanda Tangan telah dihapus",Toast.LENGTH_SHORT).show();
            }
        });

        builder.setNegativeButton("Batal", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
    private AdapterView.OnItemClickListener handleItemClick = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle("Pilih");
            builder.setItems(new CharSequence[]
                            {"Lihat data",/* "Ubah",*/ "Hapus", "Cancel"},
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            switch (which) {
                                case 0:
                                    Intent i = new Intent(mainActivity.getApplicationContext(), DataTTD.class);
                                    i.putExtra("gambar", mainActivity.recog.getArrDataImage(position));
                                    startActivity(i);
                                    break;
                                /*case 1:
                                    dialog.cancel();
                                    break;*/
                                case 1:
                                    tampilDialog2(position);
                                    break;
                                case 2:
                                    dialog.cancel();
                                    break;
                                                            }
                        }
                    });
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        }
    };

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
    private void tampilDialog() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage("Anda yakin ingin menghapus semua data?");
        builder.setPositiveButton("Hapus", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface d, int arg1) {
                mainActivity.recog.hapusSemua();
                gridViewAdapter.notifyDataSetChanged();
                Toast.makeText(context,"semua data telah dihapus",Toast.LENGTH_SHORT).show();
            }
        });

        builder.setNegativeButton("batal", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

   /* private void tampilDialogubah(final int position) {
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
}
