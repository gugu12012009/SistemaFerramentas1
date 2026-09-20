package com.example.sistemaferramentas;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "ferramentas.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_NAME = "ferramentas";
    public static final String COL_ID = "_id";
    public static final String COL_NOME = "nome";
    public static final String COL_QUANTIDADE = "quantidade";
    public static final String COL_LOCAL = "localizacao";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + TABLE_NAME + " (" +
                COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_NOME + " TEXT NOT NULL, " +
                COL_QUANTIDADE + " INTEGER, " +
                COL_LOCAL + " TEXT)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public long inserir(Ferramenta f) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL_NOME, f.getNome());
        cv.put(COL_QUANTIDADE, f.getQuantidade());
        cv.put(COL_LOCAL, f.getLocalizacao());
        long id = db.insert(TABLE_NAME, null, cv);
        db.close();
        return id;
    }

    public int atualizar(Ferramenta f) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL_NOME, f.getNome());
        cv.put(COL_QUANTIDADE, f.getQuantidade());
        cv.put(COL_LOCAL, f.getLocalizacao());
        int rows = db.update(TABLE_NAME, cv, COL_ID + "=?", new String[]{String.valueOf(f.getId())});
        db.close();
        return rows;
    }

    public int excluir(long id) {
        SQLiteDatabase db = getWritableDatabase();
        int rows = db.delete(TABLE_NAME, COL_ID + "=?", new String[]{String.valueOf(id)});
        db.close();
        return rows;
    }

    public List<Ferramenta> listarTodas() {
        List<Ferramenta> lista = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, null, null, null, null, null, COL_NOME + " ASC");
        if (cursor.moveToFirst()) {
            do {
                Ferramenta f = new Ferramenta();
                f.setId(cursor.getLong(cursor.getColumnIndexOrThrow(COL_ID)));
                f.setNome(cursor.getString(cursor.getColumnIndexOrThrow(COL_NOME)));
                f.setQuantidade(cursor.getInt(cursor.getColumnIndexOrThrow(COL_QUANTIDADE)));
                f.setLocalizacao(cursor.getString(cursor.getColumnIndexOrThrow(COL_LOCAL)));
                lista.add(f);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return lista;
    }
}
