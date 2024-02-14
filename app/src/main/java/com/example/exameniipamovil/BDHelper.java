package com.example.exameniipamovil;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class BDHelper extends SQLiteOpenHelper {
    public BDHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //CREACIÓN DE LAS TABLAS
            db.execSQL("CREATE TABLE ExamenIIPAMovil"+"(" +
                    "per_cedula INTEGER PRIMARY KEY ,"+
                    "per_nombre text NOT NULL,"+
                    "per_telefono text NOT NULL,"+
                    "per_edad INTEGER NOT NULL,"+
                    "per_Genero text NOT NULL)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //CAMBIE LA VERSIÓN DE LA TABLA DE LA BDD
        db.execSQL("DROP TABLE ExamenIIPAMovil");
        onCreate(db);
    }
}
