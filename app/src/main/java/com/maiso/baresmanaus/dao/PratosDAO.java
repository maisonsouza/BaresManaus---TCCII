package com.maiso.baresmanaus.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;

import com.maiso.baresmanaus.helper.DBHelper;
import com.maiso.baresmanaus.modelo.Pratos;
import com.maiso.baresmanaus.modelo.Usuarios;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by maiso on 10/11/2016.
 */

public class PratosDAO  {

    private static final String TABELA = "Pratos";
    private DBHelper helper;
    private Pratos pratos;

    public PratosDAO(DBHelper helper) {
        this.helper = helper;
    }

    public void insere(Pratos pratos ) {
        ContentValues values = pegadadosdoPrato(pratos);
        helper.getWritableDatabase().insert(TABELA,null,values);

    }

    @NonNull
    private ContentValues pegadadosdoPrato(Pratos pratos) {
        ContentValues values = new ContentValues();
        values.put("nome_prato",pratos.nome_prato);
        values.put("descricao",pratos.descricao);
        values.put("preco",pratos.preço);
        values.put("local_da_imagem",pratos.image_url);
        return values;
    }

    public List<Pratos> buscaPratos() {
        String sql = "SELECT * FROM "+TABELA+";";
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor c = db.rawQuery(sql,null);
        List<Pratos> pratos_cadastrados = new ArrayList<Pratos>();
        while(c.moveToNext()){
            Pratos pratos = new Pratos();
            pratos.setId(c.getLong(c.getColumnIndex("id")));
            pratos.setNome_prato(c.getString(c.getColumnIndex("nome_prato")));
            pratos.setDescricao(c.getString(c.getColumnIndex("descricao")));
            pratos.setPreço(c.getString(c.getColumnIndex("preco")));
            pratos.setImage_url(c.getString(c.getColumnIndex("local_da_imagem")));

            pratos_cadastrados.add(pratos);
        }
        c.close();
        return pratos_cadastrados;
    }

    public List<Pratos> buscaUsuariosPeloNome(String nome) {
        String sql = "SELECT * FROM "+TABELA+" WHERE nome LIKE '%"+nome+"%';";
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor c = db.rawQuery(sql,null);
        List<Pratos> usuariosencontrados = new ArrayList<Pratos>();
        while(c.moveToNext()){
            Pratos pratos = new Pratos();
            pratos.setId(c.getLong(c.getColumnIndex("id")));
            pratos.setNome_prato(c.getString(c.getColumnIndex("nome_prato")));
            pratos.setDescricao(c.getString(c.getColumnIndex("descricao")));
            pratos.setPreço(c.getString(c.getColumnIndex("preco")));
            pratos.setImage_url(c.getString(c.getColumnIndex("local_da_imagem")));


            usuariosencontrados.add(pratos);
        }
        c.close();
        return usuariosencontrados;
    }

    public void deleta(Pratos pratos) {
        SQLiteDatabase db = helper.getWritableDatabase();
        String [] params = {pratos.getId().toString()};
        db.delete(TABELA,"id= ?",params);
    }

    public void altera(Usuarios usuario) {
        SQLiteDatabase db = helper.getWritableDatabase();
        String [] params = {usuario.getId().toString()};
        ContentValues dados =  pegadadosdoPrato(pratos);
        db.update(TABELA,dados,"id = ?",params);
    }
}
