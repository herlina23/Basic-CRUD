package com.herlina.uts_apotik;


import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    String[] daftar;
    String[] daftar2;
    ListView ListView01;
    Menu menu;
    protected Cursor cursor;
    DataHelper dbcenter;
    public static MainActivity ma;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button ton1 = (Button) findViewById(R.id.button1);
        Button ton2 = (Button) findViewById(R.id.button2);
        Button ton3 = (Button) findViewById(R.id.button3);
        ton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
// TODO Auto-generated method stub
                Intent inte = new Intent(MainActivity.this, TambahObat.class);
                startActivity(inte);
            }
        });
        ton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
// TODO Auto-generated method stub
                Intent inte2 = new Intent(MainActivity.this, TambahType.class);
                startActivity(inte2);
            }
        });
        ton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
// TODO Auto-generated method stub
                Intent inte2 = new Intent(MainActivity.this, TambahPabrik.class);
                startActivity(inte2);
            }
        });

        ma = this;
        dbcenter = new DataHelper(this);
        RefreshList();
    }
    public void RefreshList(){
        SQLiteDatabase db = dbcenter.getReadableDatabase();
        cursor = db.rawQuery("SELECT obat.id_obat,obat.nama_obat,type.id_type,type.nama_type, pabrik.id_pabrik,pabrik.nama_pabrik FROM obat join type on obat.id_type=type.id_type join pabrik on obat.id_pabrik=pabrik.id_pabrik",null);
        daftar = new String[cursor.getCount()];
        daftar2 = new String[cursor.getCount()];
        cursor.moveToFirst();
        for (int cc=0; cc < cursor.getCount(); cc++){
            cursor.moveToPosition(cc);
            daftar[cc] =cursor.getString(1).toString();
            daftar2[cc] =cursor.getString(2).toString();
        }
        ListView01 = (ListView)findViewById(R.id.listView1);
        ListView01.setAdapter(new ArrayAdapter(this,
                android.R.layout.simple_list_item_1, daftar));
        ListView01.setSelected(true);
        ListView01.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView arg0, View arg1, int arg2, long
                    arg3) {
                final String selection = daftar[arg2];
//.getItemAtPosition(arg2).toString();
                final String selection2 = daftar2[arg2];
                final String selection3 = daftar2[arg2];
                final CharSequence[] dialogitem = {"Lihat Obat", "Update Obat",
                        "Update Type","Update Pabrik", "Hapus Data"};
                AlertDialog.Builder builder = new
                        AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Pilihan");
                builder.setItems(dialogitem, new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface dialog, int item) {
                        switch(item){
                            case 0 :
                                Intent i = new Intent(getApplicationContext(),
                                        LihatObat.class);
                                i.putExtra("nama_obat", selection);
                                startActivity(i);
                                break;
                            case 1 :
                                Intent in = new Intent(getApplicationContext(),
                                        UpdateObat.class);
                                in.putExtra("nama_obat", selection);
                                startActivity(in);
                                break;
                            case 2 :
                                Intent in2 = new Intent(getApplicationContext(),
                                        UpdateType.class);
                                in2.putExtra("id_type", selection2);
                                startActivity(in2);
                                break;

                            case 3 :
                                Intent in3 = new Intent(getApplicationContext(),
                                        UpdatePabrik.class);
                                in3.putExtra("id_pabrik", selection3);
                                startActivity(in3);
                                break;

                            case 4 :
                                SQLiteDatabase db = dbcenter.getWritableDatabase();
                                db.execSQL("delete from obat where nama_obat = '"+selection+"'");
                                RefreshList();
                                break;
                        }
                    }
                });
                builder.create().show();
            }});
        ((ArrayAdapter)ListView01.getAdapter()).notifyDataSetInvalidated();
        Toast.makeText(getApplicationContext(), "Data refreshed",
                Toast.LENGTH_LONG).show();

    }
}
