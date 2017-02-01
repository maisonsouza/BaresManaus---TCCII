package com.maiso.baresmanaus.helper;

/**
 * Created by maiso on 11/01/2017.
 */

import android.database.sqlite.SQLiteOpenHelper;

        import android.content.Context;
        import android.database.sqlite.SQLiteDatabase;
        import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by maiso on 10/12/2016.
 */

public class DBHelper extends SQLiteOpenHelper {


    private static final String TABELA_USUARIOS = "Usuarios";
    private static final String TABELA_SUGESTOES = "Sugestoes";
    private static final String TABELA_PRATOS = "Pratos";

    private static String DATABASE = "DB";
    private static  int VERSAO = 16;
    private static String TABELA;


    String sql_usuarios = "CREATE TABLE " + TABELA_USUARIOS + " ("
            + "id INTEGER PRIMARY KEY AUTOINCREMENT, " + "nome TEXT, "
            + "login TEXT UNIQUE NOT NULL, " + "senha TEXT, " + "tipo_usuario TEXT, "
            + "foto TEXT" + ");";
    String sql_sugestoes = "CREATE TABLE " + TABELA_SUGESTOES + " ("
            + "id INTEGER PRIMARY KEY AUTOINCREMENT, " + "autor TEXT, "
            + "sugestao TEXT, " + "nota_atendimento REAL" + ");";

    String sql_pratos = "CREATE TABLE " + TABELA_PRATOS + " ("
            + "id INTEGER PRIMARY KEY AUTOINCREMENT, " + "nome_prato TEXT, "
            + "descricao TEXT UNIQUE NOT NULL, " + "preco TEXT, " + "local_da_iamgem TEXT" +");";

    public DBHelper(Context context) {
        super(context, DATABASE, null, VERSAO);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(sql_usuarios);
        db.execSQL(sql_sugestoes);
        db.execSQL(sql_pratos);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABELA_USUARIOS);
        db.execSQL("DROP TABLE IF EXISTS " + TABELA_SUGESTOES);
        db.execSQL("DROP TABLE IF EXISTS " + TABELA_PRATOS);
        onCreate(db);

    }

    public static String getTabela() {
        return TABELA;
    }
}
