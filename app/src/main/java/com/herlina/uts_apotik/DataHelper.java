package com.herlina.uts_apotik;

/**
 * Created by User on 11/15/2017.
 */

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DataHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "obat.db";
    private static final int DATABASE_VERSION = 1;
    public DataHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
// TODO Auto-generated constructor stub
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
// TODO Auto-generated method stub
        String sql = "create table obat(id_obat integer primary key,nama_obat text null, harga integer null,id_type integer not null,id_pabrik integer not null,foreign key(id_type) references type(id_type),foreign key(id_pabrik) references pabrik(id_pabrik))";
        Log.d("Data obat", "onCreate: " + sql);
        db.execSQL(sql);

        String sql2 = "create table type(id_type integer primary key,nama_type text);";
        Log.d("Data obat", "onCreate: " + sql);
        db.execSQL(sql2);

        String sql3 = "create table pabrik(id_pabrik integer primary key,nama_pabrik text);";
        Log.d("Data obat", "onCreate: " + sql);
        db.execSQL(sql3);

        String sql4 = "INSERT INTO type(id_type, nama_type)VALUES ('1', 'stimulan'),('2', 'ekspetoran');";
        db.execSQL(sql4);

        String sql5 = "INSERT INTO pabrik(id_pabrik, nama_pabrik)VALUES ('1', 'kimia farma'),('2', 'kalbe farma');";
        db.execSQL(sql5);

        String sql6 = "INSERT INTO obat(id_obat,nama_obat,harga,id_type,id_pabrik)VALUES ('1', 'neurobion','2300000','1','1'),('2', 'Fix OBH','4300000','2','2');";
        db.execSQL(sql6);
    }
    @Override
    public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2)
    {
// TODO Auto-generated method stub
    }
}
