package com.example.cecilia.api_clima;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Cecilia on 20/11/2017.
 */

public class CiudadOpenHelper extends SQLiteOpenHelper
{
    private static final int DATABASE_VERSION = 1;

    public static String getCiudadesTableName()
    {
        return CIUDADES_TABLE_NAME;
    }

    private static final String CIUDADES_TABLE_NAME = "Ciudades";
    private String sqlCrear = "CREATE TABLE Ciudades (id INTEGER PRIMARY KEY  NOT NULL, ciudad TEXT)";


    public CiudadOpenHelper(Context context, String nombre, SQLiteDatabase.CursorFactory factory, int version)
    {
        super(context, nombre, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL(sqlCrear);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int anterior, int nueva) {
        db.execSQL("DROP TABLE IF EXISTS TCiudades");
        db.execSQL(sqlCrear);
    }
}
