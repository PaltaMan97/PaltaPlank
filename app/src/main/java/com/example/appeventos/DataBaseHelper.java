package com.example.appeventos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DataBaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "eventosDB";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_USUARIOS = "usuarios";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NOMBRE_USUARIO = "nombre_usuario";
    private static final String COLUMN_CONTRASENA = "contrasena";
    private static final String COLUMN_RESPUESTA_SECRETA = "respuesta_secreta";

    private static final String TABLE_EVENTOS = "eventos";
    private static final String COLUMN_TITULO = "titulo";
    private static final String COLUMN_FECHA = "fecha";
    private static final String COLUMN_IMPORTANCIA = "importancia";
    private static final String COLUMN_OBSERVACION = "observacion";
    private static final String COLUMN_LUGAR = "lugar";
    private static final String COLUMN_TIEMPO_AVISO = "tiempo_aviso";
    private static final String COLUMN_USUARIO_ID = "usuario_id";

    private SQLiteDatabase database;

    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            String createUsuariosTable = "CREATE TABLE " + TABLE_USUARIOS + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_NOMBRE_USUARIO + " TEXT NOT NULL UNIQUE, " +
                    COLUMN_CONTRASENA + " TEXT NOT NULL, " +
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
        try {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_EVENTOS);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_USUARIOS);
            onCreate(db);
        } catch (SQLException e) {
            Log.e("DBHelper", "Error al actualizar la base de datos", e);
        }
    }

    public void open() {
        database = this.getWritableDatabase();
    }

    public void close() {
        this.close();
    }

    // Métodos para interactuar con la tabla Usuarios
    public long createUser(Usuario usuario) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_NOMBRE_USUARIO, usuario.getNombreUsuario());
        values.put(COLUMN_CONTRASENA, usuario.getContrasena());
        values.put(COLUMN_RESPUESTA_SECRETA, usuario.getRespuestaSecreta());
        return database.insert(TABLE_USUARIOS, null, values);
    }

    public Usuario getUser(int id) {
        Cursor cursor = database.query(TABLE_USUARIOS, new String[] { COLUMN_ID, COLUMN_NOMBRE_USUARIO, COLUMN_CONTRASENA, COLUMN_RESPUESTA_SECRETA }, COLUMN_ID + " = ?", new String[] { String.valueOf(id) }, null, null, null);
        if (cursor.moveToFirst()) {
            Usuario usuario = new Usuario();
            usuario.setId(cursor.getInt(0));
            usuario.setNombreUsuario(cursor.getString(1));
            usuario.setContrasena(cursor.getString(2));
            usuario.setRespuestaSecreta(cursor.getString(3));
            return usuario;
        }
        return null;
    }

    public void updateUser(Usuario usuario) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_NOMBRE_USUARIO, usuario.getNombreUsuario());
        values.put(COLUMN_CONTRASENA, usuario.getContrasena());
        values.put(COLUMN_RESPUESTA_SECRETA, usuario.getRespuestaSecreta());
        database.update(TABLE_USUARIOS, values, COLUMN_ID + " = ?", new String[] { String.valueOf(usuario.getId()) });
    }

    public void deleteUser(int id) {
        database.delete("Usuarios", "id = ?", new String[] { String.valueOf(id) });
    }

}

