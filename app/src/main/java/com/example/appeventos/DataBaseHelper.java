package com.example.appeventos;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DataBaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "eventosDB";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_USUARIOS = "usuarios";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NOMBRE_USUARIO = "nombreUsuario";
    private static final String COLUMN_CONTRASENA = "contrasena";
    private static final String COLUMN_PREGUNTA_SECRETA = "preguntaSecreta";
    private static final String COLUMN_RESPUESTA_SECRETA = "respuestaSecreta";

    private static final String TABLE_EVENTOS = "eventos";
    private static final String COLUMN_TITULO = "titulo";
    private static final String COLUMN_FECHA = "fecha";
    private static final String COLUMN_IMPORTANCIA = "importancia";
    private static final String COLUMN_OBSERVACION = "observacion";
    private static final String COLUMN_LUGAR = "lugar";
    private static final String COLUMN_TIEMPO_AVISO = "tiempoAviso";
    private static final String COLUMN_USUARIO_ID = "usuarioId";

    public  DataBaseHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            String createUsuariosTable = "CREATE TABLE " + TABLE_USUARIOS + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_NOMBRE_USUARIO + " TEXT NOT NULL UNIQUE, " +
                    COLUMN_CONTRASENA + " TEXT NOT NULL, " +
                    COLUMN_PREGUNTA_SECRETA + " TEXT NOT NULL, " +
                    COLUMN_RESPUESTA_SECRETA + " TEXT NOT NULL)";

            String createEventosTable = "CREATE TABLE " + TABLE_EVENTOS + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_TITULO + " TEXT NOT NULL, " +
                    COLUMN_FECHA + " TEXT NOT NULL, " +
                    COLUMN_IMPORTANCIA + " TEXT NOT NULL, " +
                    COLUMN_OBSERVACION + " TEXT, " +
                    COLUMN_LUGAR + " TEXT, " +
                    COLUMN_TIEMPO_AVISO + " INTEGER NOT NULL, " +
                    COLUMN_USUARIO_ID + " INTEGER NOT NULL, " +
                    "FOREIGN KEY (" + COLUMN_USUARIO_ID + ") REFERENCES " + TABLE_USUARIOS + "(" + COLUMN_ID + ") ON DELETE CASCADE)";

            db.execSQL(createUsuariosTable);
            db.execSQL(createEventosTable);
        } catch (SQLException e) {
            Log.e("DBHelper", "Error al crear la base de datos", e);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        try{
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_EVENTOS);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_USUARIOS);
            onCreate(db);
        } catch (SQLException e) {
            Log.e("DBHelper", "Error al actualizar la base de datos", e);
        }
    }
}
